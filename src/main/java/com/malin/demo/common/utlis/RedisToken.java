package com.malin.demo.common.utlis;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 生成token
 * @author Administrator
 *
 */
@Component
public class RedisToken {

	@Autowired
	private BaseRedisService baseRedisService;
	
	private static Long tokenTimeOut= (long) (60 * 60);
	public String getToken() {
		
		String token= ""+UUID.randomUUID();
		String replaceAll = token.replaceAll("-", "");
		
		baseRedisService.setString(replaceAll, replaceAll,tokenTimeOut);
		return replaceAll;
	}
	
	
	// 1.在调用接口之前生成对应的令牌(Token), 存放在Redis
		// 2.调用接口的时候，将该令牌放入的请求头中
		// 3.接口获取对应的令牌,如果能够获取该令牌(将当前令牌删除掉) 就直接执行该访问的业务逻辑
		// 4.接口获取对应的令牌,如果获取不到该令牌 直接返回请勿重复提交
		public synchronized boolean findToken(String tokenKey) {
			// 3.接口获取对应的令牌,如果能够获取该(从redis获取令牌)令牌(将当前令牌删除掉) 就直接执行该访问的业务逻辑
			String tokenValue = (String) baseRedisService.getString(tokenKey);
			if (StringUtils.isEmpty(tokenValue)) {
				return false;
			}
			// 保证每个接口对应的token 只能访问一次，保证接口幂等性问题
			baseRedisService.delKey(tokenValue);
			return true;
		}
	
}
