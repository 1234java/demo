package com.malin.demo.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.malin.demo.common.aop.DateUtil;
import com.malin.demo.common.aop.FileUtil;
import com.malin.demo.common.utlis.BaseRedisService;
import com.malin.demo.common.utlis.ConstantUtils;
import com.malin.demo.common.utlis.RedisToken;
import com.malin.demo.service.ExtApiIdempotent;
@Controller
public class IndexController {

	@Autowired
	private RedisToken redisToken;
	
	@RequestMapping(value = "/token")
	@ResponseBody
	public String getToken() {
		String token = redisToken.getToken();
		return token;
	}
	
	
	@RequestMapping(value = "/addOrderExtApiIdempotent", produces = "application/json; charset=utf-8")
	//@ExtApiIdempotent(type = ConstantUtils.EXTAPIHEAD)
	@ResponseBody
	@ExtApiIdempotent(value = ConstantUtils.EXTAPIHEAD)
	public String addOrderExtApiIdempotent( HttpServletRequest request) {
		/*
		 * String header = request.getHeader("token"); if(StringUtils.isEmpty(header)) {
		 * return "参数错误"; } boolean findToken = redisToken.findToken(header);
		 * if(!findToken) { return "请勿重复提交"; }
		 */
		
		
		
		//int result = orderMapper.addOrder(orderEntity);
		int result =1;
		return result > 0 ? "添加成功" : "添加失败" + "";
	}
 
	
	
	
	
	@RequestMapping(value = "/file/upload")
	@ResponseBody
	@CrossOrigin(origins = "*") 
	//@RequestParam(value = "file") MultipartFile file, HttpServletRequest request,HttpServletResponse response , 
	public String uplsoad(@RequestParam(value = "file") MultipartFile file) throws Exception {
		//System.out.println(name);
	//	String parameter = request.getParameter("file");
	//	System.out.println(parameter);MultipartHttpServletRequest request
		System.out.println(file.getName());
			 JSONObject json =new JSONObject();
			 
			 Map<String, Object> map=new HashMap<String, Object>();
			 map.put("errno", 0);
			 map.put("data", "sdsd");
			 
			 String jsonString = JSONObject.toJSONString(map);
			 System.out.println(jsonString);
		return jsonString;
	}
	
	@RequestMapping(value = "/uploadUsers",method = RequestMethod.POST)
	@ResponseBody
	@CrossOrigin(origins = "*") 
	public String uploadUsers(@RequestParam(value = "myFileName", required = true) MultipartFile file, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(!file.isEmpty()){
			// 获取文件名
			String fileName = file.getOriginalFilename();
			//
			String userImageFilePath="E://userImages//";
			
			System.out.println("--"+userImageFilePath);
			// 获取文件的后缀名
			String suffixName = fileName.substring(fileName.lastIndexOf("."));
			String newFileName=DateUtil.getCurrentDateStr()+suffixName;
			File destFile =new File(userImageFilePath+newFileName);
			FileUtil.uploadFile(file.getBytes(), userImageFilePath, newFileName);
			System.out.println(destFile);
			//虚拟路径(是需要保存到数据库中的)
			String filePath="/userImage/"+newFileName;
			 JSONObject json =new JSONObject();
			 
			 Map<String, Object> map=new HashMap<String, Object>();
			 map.put("errno", 0);
			 map.put("data", filePath);
			 
			 String jsonString = JSONObject.toJSONString(map);
			 System.out.println(jsonString);
			 return jsonString;	
		}else {
			 return null;	
		}
		
	}
	
}
