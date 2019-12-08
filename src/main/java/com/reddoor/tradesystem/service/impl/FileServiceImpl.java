package com.reddoor.tradesystem.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.reddoor.tradesystem.service.FileService;
import com.reddoor.tradesystem.util.FileUtil;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public Map<String, Object> uploadFile(MultipartFile uploadFile)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			if(uploadFile != null && uploadFile.getOriginalFilename() != null && uploadFile.getOriginalFilename().length()>0){
				String fileName = uploadFile.getOriginalFilename();
				String filePath = "E:\\temp\\upload\\file\\";
				FileUtil fu = new FileUtil();
				String newName = fu.newFile(filePath, fileName);
				
				File file = new File(filePath+newName);
				uploadFile.transferTo(file);
				
				resultMap.put("error", 0);
				resultMap.put("url", "/file/" + newName);
				return resultMap;
			}
			else{
				resultMap.put("error", 1);
				resultMap.put("message", "文件异常");
				return resultMap;
			}
		}
		catch(Exception ex){
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传发生异常");
			return resultMap;
		}
				 
	}

	@Override
	public boolean deleteFile(String fileName) throws Exception {
		fileName = fileName.substring(fileName.lastIndexOf("/")+1);
		fileName = "E:\\temp\\upload\\file\\" + fileName;
		FileUtil.deleteFile(fileName);
		
		return true;
	}

}
