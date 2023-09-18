package com.like.system.file.application.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.file.adapter.out.file.FileConverterUtil;
import com.like.system.file.adapter.out.file.WebServerStaticRepository;
import com.like.system.file.application.port.in.WebServerDownloadUseCase;

import jakarta.servlet.http.HttpServletResponse;

@Service
@Transactional
public class WebServerDownloadService implements WebServerDownloadUseCase {
	
	WebServerStaticRepository repository;	
	
	WebServerDownloadService(WebServerStaticRepository repository) {		
		this.repository = repository;		
	}	
		
	@Override
	public void downloadWebStaticPath(String uploadFileName, String downloadFileName, HttpServletResponse response) {
		File file = repository.getStaticPathFile(uploadFileName);
		
		try {
			setResponse(response, file.length(), downloadFileName);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		
		try {
			FileConverterUtil.fileToStream(file, response.getOutputStream());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
