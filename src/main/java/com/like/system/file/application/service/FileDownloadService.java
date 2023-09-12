package com.like.system.file.application.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.file.adapter.out.file.FileConverterUtil;
import com.like.system.file.application.port.in.FileDownloadUseCase;
import com.like.system.file.application.port.out.FileInfoCommandDbPort;
import com.like.system.file.domain.FileInfo;

import jakarta.servlet.http.HttpServletResponse;

@Service
@Transactional
public class FileDownloadService implements FileDownloadUseCase {

	FileInfoCommandDbPort port;
	
	FileDownloadService(FileInfoCommandDbPort port) {
		this.port = port;
	}
	
	@Override
	public void downloadFile(String fileInfoId, HttpServletResponse response) {
		FileInfo fileInfo = this.port.getFileInfo(fileInfoId);
		File file = new File(fileInfo.getPath(), fileInfo.getUuid());
		
		try {
			setResponse(response, fileInfo.getSize(), fileInfo.getFileName());
		} catch (Exception e) {		
			e.printStackTrace();
		}
		
		try (OutputStream os = response.getOutputStream()) {
			try {
				FileConverterUtil.fileToStream(file, os);
			} catch (FileNotFoundException e) {			
				e.printStackTrace();
			} catch (IOException e) {			
				e.printStackTrace();
			}
		} catch (IOException e1) {		
			e1.printStackTrace();
		}
						
		// 다운로드 카운트 + 1
		fileInfo.plusDownloadCount();
		
		//fileInfoRepository.save(fileInfo);
		this.port.save(fileInfo);
	}

	
	private HttpServletResponse setResponse(HttpServletResponse response, long fileSize, String fileName) throws Exception {
		
		// get MIME type of the file
		String mimeType= null;
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";	         
		}
		
		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLengthLong(fileSize);
		response.setCharacterEncoding("UTF-8");
		
		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment;filename=\"%s\"", new String(fileName.getBytes("EUC-KR"), "8859_1"));
		
		response.setHeader(headerKey, headerValue);
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");	
		
		return response;
	}
	
}
