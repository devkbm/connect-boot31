package com.like.system.file.adapter.out.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.like.system.file.config.FileServerProperties;

@Repository
public class FileServerRepository {
		
	private FileServerProperties properties;
		
	public static String fileDownLoadUrl;		
	
	public FileServerRepository(FileServerProperties properties) {
		this.properties = properties;
		FileServerRepository.fileDownLoadUrl = properties.getClientDownloadUrl(); 
	}	
	
	public String getFileServerUploadPath() {	
		return this.properties.getLocation();
	}
			
	public String fileTransfer(MultipartFile sourceFile, String serverFileName) throws FileNotFoundException, IOException {		
		File file = copy(sourceFile, getFileServerUploadPath(), serverFileName);
											
		return file.getPath();
	}
			
	
	public boolean deleteFile(File file) throws FileNotFoundException {				
		if(!isExists(file)) throw new FileNotFoundException();
		
		return file.delete();		
	}		
	
	private boolean isExists(File file) {					
		return file == null || !file.exists() ? false : true;
	}
	
	private File copy(MultipartFile sourceFile, String fileServerPath, String serverFileName) throws FileNotFoundException, IOException {
		if(sourceFile == null || sourceFile.isEmpty()) throw new FileNotFoundException();
		
		File file = new File(fileServerPath, serverFileName);
								
		try (ReadableByteChannel	cin = Channels.newChannel(sourceFile.getInputStream());	
			 FileOutputStream 		fos = new FileOutputStream(file);
			 FileChannel 			cout = fos.getChannel();) {			
						
			 cout.transferFrom(cin, 0, sourceFile.getInputStream().available());						 				
		}	
		
		return file;
	
	}
	
}
