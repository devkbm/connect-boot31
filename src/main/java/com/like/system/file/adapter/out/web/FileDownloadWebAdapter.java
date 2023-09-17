package com.like.system.file.adapter.out.web;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.like.system.file.application.port.out.FileDownloadWebPort;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class FileDownloadWebAdapter implements FileDownloadWebPort {

	@Override
	public HttpServletResponse setResponse(HttpServletResponse response, long fileSize, String fileName, String mimeType) {
		// get MIME type of the file		
		if (StringUtils.hasText(mimeType)) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";	         
		}
		
		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLengthLong(fileSize);
		response.setCharacterEncoding("UTF-8");
		
		// set headers for the response
		String headerFileName = "";
		try {
			headerFileName = new String(fileName.getBytes("EUC-KR"), "8859_1");			
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}				
		
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment;filename=\"%s\"", headerFileName);
		
		response.setHeader(headerKey, headerValue);
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");	
		
		return response;
	}

}
