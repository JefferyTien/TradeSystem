package com.reddoor.tradesystem.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	/**
	 * 上传文件
	 * @param uploadFile
	 * @return 上传信息
	 * @throws Exception
	 */
	public Map<String, Object> uploadFile(MultipartFile uploadFile) throws Exception;
	
	/**
	 * 删除文件
	 * @param fileName
	 * @return 
	 * @throws Exception
	 */
	public boolean deleteFile(String fileName) throws Exception;
}
