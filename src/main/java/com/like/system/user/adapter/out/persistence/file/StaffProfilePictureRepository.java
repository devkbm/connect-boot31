package com.like.system.user.adapter.out.persistence.file;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.like.system.file.application.port.in.FileServerDeleteUseCase;
import com.like.system.file.application.port.in.FileServerUploadUseCase;
import com.like.system.file.domain.FileInfo;
import com.like.system.user.domain.ProfilePictureRepository;

@Repository
public class StaffProfilePictureRepository implements ProfilePictureRepository {

	FileServerUploadUseCase uploadUseCase;
	FileServerDeleteUseCase deleteUseCase;
	
	public StaffProfilePictureRepository(FileServerUploadUseCase uploadUseCase
										,FileServerDeleteUseCase deleteUseCase) {
		this.uploadUseCase = uploadUseCase;
		this.deleteUseCase = deleteUseCase;
	}
	
	@Override
	public String upload(MultipartFile sourceFile) {
		FileInfo fileInfo = uploadUseCase.uploadFile(sourceFile, "kbm", "SystemUser");
		return fileInfo.getId().toString();		
	}

	@Override
	public void delete(String path) {		
		deleteUseCase.delete(path);				
	}

}
