package com.like.system.file.adapter.in.web;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.like.system.file.application.port.in.FileDownloadUseCase;

@Controller
public class FileDownloadController {
			
	private FileDownloadUseCase fileService;	
			
	public FileDownloadController(FileDownloadUseCase fileService) {		
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
			
}