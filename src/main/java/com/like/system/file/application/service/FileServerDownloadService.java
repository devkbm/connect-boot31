package com.like.system.file.application.service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.file.adapter.out.file.FileConverterUtil;
import com.like.system.file.application.port.in.FileServerDownloadUseCase;
import com.like.system.file.application.port.out.FileDownloadWebPort;
import com.like.system.file.application.port.out.FileInfoCommandDbPort;
import com.like.system.file.domain.FileInfo;

import jakarta.servlet.http.HttpServletResponse;

@Service
@Transactional
public class FileServerDownloadService implements FileServerDownloadUseCase {

	FileInfoCommandDbPort dbPort;	
	FileDownloadWebPort webPort;
	
	FileServerDownloadService(FileInfoCommandDbPort dbPort
							 ,FileDownloadWebPort webPort) {
		this.dbPort = dbPort;			
		this.webPort = webPort;
	}	
	
	@Override
	public void download(String fileInfoId, HttpServletResponse response) {		
		FileInfo fileInfo = this.dbPort.getFileInfo(fileInfoId);
		File file = new File(fileInfo.getPath(), fileInfo.getUuid());
		
		this.webPort.setResponse(response, fileInfo.getSize(), fileInfo.getFileName(), "application/octet-stream");
		
		try (OutputStream os = response.getOutputStream()) {			
			FileConverterUtil.fileToStream(file, os);			
		} catch (IOException e1) {		
			e1.printStackTrace();
		}
						
		// 다운로드 카운트 + 1
		fileInfo.plusDownloadCount();
				
		this.dbPort.save(fileInfo);
	}

	@Override
	public void viewImage(String fileInfoId, HttpServletResponse response) {
		FileInfo fileInfo = this.dbPort.getFileInfo(fileInfoId);
		File file = new File(fileInfo.getPath(), fileInfo.getUuid());
		
		this.webPort.setImageResponse(response, fileInfo.getSize(), fileInfo.getContentType());
		
		try (OutputStream os = response.getOutputStream()) {			
			FileConverterUtil.fileToStream(file, os);			
		} catch (IOException e1) {		
			e1.printStackTrace();
		}		
	}	
	
}
