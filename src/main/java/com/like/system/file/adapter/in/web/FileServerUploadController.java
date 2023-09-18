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

import com.like.system.file.adapter.out.file.FileServerRepository;
import com.like.system.file.application.port.dto.FileResponseDTO;
import com.like.system.file.application.port.in.FileServerUploadUseCase;
import com.like.system.file.domain.FileInfo;

@Controller
public class FileServerUploadController {
				
	FileServerUploadUseCase useCase;
			
	public FileServerUploadController(FileServerUploadUseCase useCase) {
		this.useCase = useCase;
	}
	
	@PostMapping("/api/system/file")
	public ResponseEntity<?> fileUpload(final MultipartHttpServletRequest request
									   ,@RequestParam(value="appUrl", required=false) String appUrl ) throws Exception {
						
		List<MultipartFile> multipartFiles = convert(request);		
								
		List<FileInfo> list = useCase.uploadFile(multipartFiles, "kbm", appUrl);
		
		List<FileResponseDTO> fileList = toDTO(list);
				
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
						 					
		return new ResponseEntity<List<FileResponseDTO>>(fileList, responseHeaders, HttpStatus.OK);
	}
		
	@PostMapping("/api/system/file2")
	public void handleFileUpload(@RequestParam("file") MultipartFile file) throws Exception {

		useCase.uploadFile(file, "kbm", "test");
	}			
	
	private List<MultipartFile> convert(MultipartHttpServletRequest request) {				
		final Map<String, MultipartFile> fileMap = request.getFileMap();
		Iterator<Entry<String,MultipartFile>> itr = fileMap.entrySet().iterator();		
		List<MultipartFile> multipartFiles = new ArrayList<>(fileMap.entrySet().size());
		
		while ( itr.hasNext() ) {			
			Entry<String,MultipartFile> entry = itr.next();
			multipartFiles.add(entry.getValue());																	
		}					
		
		return multipartFiles;
	}
	
	private List<FileResponseDTO> toDTO(List<FileInfo> list) {
		List<FileResponseDTO> fileList = new ArrayList<>();
		
		for (FileInfo info : list) {
			Map<String, String> res = new HashMap<>();
			res.put("status", "success");
			
			Map<String, String> link = new HashMap<>();
			link.put("download", FileServerRepository.fileDownLoadUrl+info.getId());
			
			FileResponseDTO response = FileResponseDTO.convert(info);
			fileList.add(response);
		}
		return fileList;
	}
			
}
