package com.malin.demo.common.aop;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {
	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception { 
        File targetFile = new File(filePath);  
        if(!targetFile.exists() && targetFile.isDirectory()){    // 判断/download目录是否存在
            targetFile.mkdirs();    // 创建目录
        }       
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
