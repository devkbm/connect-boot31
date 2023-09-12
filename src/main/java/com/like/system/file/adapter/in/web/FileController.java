package com.like.system.file.adapter.in.web;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.like.system.file.application.port.in.FileDownloadUseCase;
import com.like.system.file.application.service.FileService;
import com.like.system.file.domain.FileInfo;

@Controller
public class FileController {
			
	private FileDownloadUseCase fileService;	
			
	public FileController(FileDownloadUseCase fileService) {		
		this.fileService = fileService;
	}
		
	@GetMapping("/api/system/file/{id}")
	public HttpServletResponse fileDownLoad(HttpServletResponse response
										   ,@PathVariable String id) throws Exception {								
							
		fileService.downloadFile(id, response);		
		
		return response;
	}
	
	
	@GetMapping("/api/system/fileimage/{id}")
	public HttpServletResponse fileImageDownLoad(HttpServletResponse response
												,@PathVariable String id) throws Exception {
		/*	
		FileInfo fileInfo = fileService.getFileInfo(id);
					
		// set content attributes for the response
		response.setContentType(fileInfo.getContentType());
		response.setContentLengthLong(fileInfo.getSize());
		response.setCharacterEncoding("UTF-8");
									
		fileService.downloadFile(fileInfo, response.getOutputStream());		
		*/
		return response;
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