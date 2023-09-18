package com.like.system.file.adapter.out.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.like.system.file.config.FileServerProperties;

@Repository
public class WebServerStaticRepository {
		
	private FileServerProperties properties;
		
	public static String fileDownLoadUrl;		
	
	public WebServerStaticRepository(FileServerProperties properties) {
		this.properties = properties;
		WebServerStaticRepository.fileDownLoadUrl = properties.getClientDownloadUrl(); 
	}	
			
	public String getWebStaticUploadPath() {
		return this.properties.getWebStaticLocation();
	}
	
	public String fileTransfer(MultipartFile sourceFile, String serverFileName) throws FileNotFoundException, IOException {		
		File file = FileConverterUtil.copy(sourceFile, getWebStaticUploadPath(), serverFileName);
											
		return file.getPath();
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
		return file == null || !file.exists() ? false : true;
	}
	
}
