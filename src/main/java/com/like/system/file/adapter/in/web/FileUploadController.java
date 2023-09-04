package com.like.system.file.adapter.in.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.like.system.file.adapter.out.file.LocalFileRepository;
import com.like.system.file.application.port.dto.FileResponseDTO;
import com.like.system.file.application.service.FileService;
import com.like.system.file.domain.FileInfo;

@Controller
public class FileUploadController {
			
	private FileService fileService;	
			
	public FileUploadController(FileService fileService) {		
		this.fileService = fileService;
	}
		
	@PostMapping("/api/system/file")
	public ResponseEntity<?> fileUpload(final MultipartHttpServletRequest request
									   ,@RequestParam(value="appUrl", required=false) String appUrl ) throws Exception {
						
		List<FileInfo> list = new ArrayList<FileInfo>();
		final Map<String, MultipartFile> files = request.getFileMap();
		Iterator<Entry<String,MultipartFile>> itr = files.entrySet().iterator();		
				
		while ( itr.hasNext() ) {			
			Entry<String,MultipartFile> entry = itr.next(); 			
			MultipartFile file = entry.getValue();					
			list.add(fileService.uploadFile(file, "kbm", appUrl));																
		}
		
								
		List<FileResponseDTO> fileList = new ArrayList<>();
		
		for (FileInfo info : list) {
			Map<String, String> res = new HashMap<>();
			res.put("status", "success");
			
			Map<String, String> link = new HashMap<>();
			link.put("download", LocalFileRepository.fileDownLoadUrl+info.getId());
			
			FileResponseDTO response = FileResponseDTO.convert(info);
			fileList.add(response);
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
						 					
		return new ResponseEntity<List<FileResponseDTO>>(fileList, responseHeaders, HttpStatus.OK);
	}
	
	
	@PostMapping("/api/system/file2")
	public void handleFileUpload(@RequestParam("file") MultipartFile file) throws Exception {

		fileService.uploadFile(file, "kbm", "test");
	}						
			
}
