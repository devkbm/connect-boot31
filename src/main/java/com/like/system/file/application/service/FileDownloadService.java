package com.like.system.file.application.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.file.adapter.out.file.FileConverterUtil;
import com.like.system.file.adapter.out.file.FileServerRepository;
import com.like.system.file.application.port.in.FileDownloadUseCase;
import com.like.system.file.application.port.out.FileDownloadWebPort;
import com.like.system.file.application.port.out.FileInfoCommandDbPort;
import com.like.system.file.domain.FileInfo;

import jakarta.servlet.http.HttpServletResponse;

@Service
@Transactional
public class FileDownloadService implements FileDownloadUseCase {

	FileInfoCommandDbPort port;
	FileServerRepository localFileRepository;
	FileDownloadWebPort webPort;
	
	FileDownloadService(FileInfoCommandDbPort port
					   ,FileServerRepository localFileRepository
					   ,FileDownloadWebPort webPort) {
		this.port = port;
		this.localFileRepository = localFileRepository;
		this.webPort = webPort;
	}	
	
	@Override
	public void download(String fileInfoId, HttpServletResponse response) {
		FileInfo fileInfo = this.port.getFileInfo(fileInfoId);
		File file = new File(fileInfo.getPath(), fileInfo.getUuid());
		
		this.webPort.setResponse(response, fileInfo.getSize(), fileInfo.getFileName(), "application/octet-stream");
		/*
		try {
			setResponse(response, fileInfo.getSize(), fileInfo.getFileName());
		} catch (Exception e) {		
			e.printStackTrace();
		}
		*/
		try (OutputStream os = response.getOutputStream()) {			
			FileConverterUtil.fileToStream(file, os);			
		} catch (IOException e1) {		
			e1.printStackTrace();
		}
						
		// 다운로드 카운트 + 1
		fileInfo.plusDownloadCount();
		
		//fileInfoRepository.save(fileInfo);
		this.port.save(fileInfo);
	}
	
	/*
	@Override
	public void downloadFile(File file, HttpServletResponse response) {		
		try {
			FileConverterUtil.fileToStream(file, response.getOutputStream());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}							
	}	
	
	@Override
	public File getWebStaticFilePath(String fileName) {
		return localFileRepository.getStaticPathFile(fileName);		
	}
	*/
	@Override
	public void downloadWebStaticPath(String uploadFileName, String downloadFileName, HttpServletResponse response) {
		File file = localFileRepository.getStaticPathFile(uploadFileName);
		
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
