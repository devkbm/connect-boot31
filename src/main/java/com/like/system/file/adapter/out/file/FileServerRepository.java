package com.like.system.file.adapter.out.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.like.system.file.config.ServerFileProperties;

@Repository
public class FileServerRepository {
	
	public enum FileUploadLocation {
		WEB_SERVER_STATIC_PATH, FILE_SERVER_PATH
	}	
	private ServerFileProperties properties;
		
	public static String fileDownLoadUrl;		
	
	public FileServerRepository(ServerFileProperties properties) {
		this.properties = properties;
		FileServerRepository.fileDownLoadUrl = properties.getClientDownloadUrl(); 
	}	
	
	public String getFileServerUploadPath() {	
		return this.properties.getLocation();
	}
	
	public String getWebStaticUploadPath() {
		return this.properties.getWebStaticLocation();
	}
			
		
	public String fileTransfer(MultipartFile sourceFile, String fileName, FileUploadLocation location) throws FileNotFoundException, IOException {
		String path = null;		
					
		if ( location == FileUploadLocation.FILE_SERVER_PATH) {
			path = this.getFileServerUploadPath();
		} else {
			path = this.getWebStaticUploadPath();
		}
		
		File file = FileConverterUtil.convertAndCopy(sourceFile, path, fileName);
								
		FileConverterUtil.streamToFile(sourceFile.getInputStream(), file);	
		
		return file.getPath();
	}	
	
	/**
	 * 파일을 삭제한다.
	 * @param file 
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean deleteFile(File file) throws FileNotFoundException {				
		if(!isExists(file)) throw new FileNotFoundException();
						
		return file.delete();		
	}	
	
	public File getStaticPathFile(String fileName) {
		return new File(this.getWebStaticUploadPath(), fileName);
	}
	
	public boolean deleteStaticFile(String fileName) throws FileNotFoundException {
		File file = this.getStaticPathFile(fileName);
		
		if(!isExists(file)) throw new FileNotFoundException();
		
		return file.delete();
	}	
	
	private boolean isExists(File file) {
		if(file == null || !file.exists()) {
			return false;
		}
		
		return true;
	}
	
}
