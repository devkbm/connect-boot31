package com.like.cooperation.board.application.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.board.application.dto.ArticleSaveDTO;
import com.like.cooperation.board.application.dto.ArticleSaveMultipartDTO;
import com.like.cooperation.board.application.port.in.ArticleSaveUseCase;
import com.like.cooperation.board.application.port.out.ArticleCommandDbPort;
import com.like.cooperation.board.application.port.out.BoardCommandDbPort;
import com.like.cooperation.board.domain.Article;
import com.like.cooperation.board.domain.ArticleAttachedFile;
import com.like.cooperation.board.domain.AttachedFileConverter;
import com.like.cooperation.board.domain.Board;
import com.like.system.core.util.SessionUtil;
import com.like.system.file.application.port.in.FileServerSelectUseCase;
import com.like.system.file.application.port.in.FileServerUploadUseCase;
import com.like.system.file.domain.FileInfo;

@Transactional
@Service
public class ArticleSaveService implements ArticleSaveUseCase {

	ArticleCommandDbPort dbPort;
	BoardCommandDbPort boardDbPort;
	FileServerUploadUseCase uploadUseCase;
	FileServerSelectUseCase fileSelectUseCase;
	
	ArticleSaveService(ArticleCommandDbPort dbPort,
					   BoardCommandDbPort boardDbPort,
					   FileServerUploadUseCase uploadUseCase,
				       FileServerSelectUseCase fileSelectUseCase) {
		this.dbPort = dbPort;
		this.boardDbPort = boardDbPort;
		this.uploadUseCase = uploadUseCase;
		this.fileSelectUseCase = fileSelectUseCase;
	}
	
	@Override
	public void save(ArticleSaveDTO dto) {
		Board board = boardDbPort.select(dto.boardId());
		if (board == null) throw new IllegalArgumentException("존재 하지 않은 게시판입니다.");
		
		List<FileInfo> fileInfoList = Collections.emptyList();
		List<ArticleAttachedFile> attachedFileList = Collections.emptyList();
		
		Article entity = dto.newArticle(board); 
		
		// 2. 저장된 파일 리스트를 조회한다.
		// 3. FileInfo를 AttachedFile로 변환한다.
		if (dto.attachFile() != null) {
			fileInfoList = fileSelectUseCase.select(dto.attachFile());
			
			attachedFileList = AttachedFileConverter.convert(entity, fileInfoList);				
			if (!attachedFileList.isEmpty()) entity.setFiles(attachedFileList);
		}
		
		this.dbPort.save(entity);		
	}

	@Override
	public void save(ArticleSaveMultipartDTO dto) {
		Board board = boardDbPort.select(dto.boardId());
		if (board == null) throw new IllegalArgumentException("존재 하지 않은 게시판입니다.");
		
		List<FileInfo> fileInfoList = null;
		List<ArticleAttachedFile> attachedFileList = null;					
		
		Article article = dto.newArticle(board);
		
		// 첨부파일 저장
		if (!dto.file().isEmpty()) {		
			String userId = SessionUtil.getUserId();
			
			fileInfoList = uploadUseCase.uploadFile(dto.file(), userId, "board");
			
			attachedFileList = AttachedFileConverter.convert(article, fileInfoList);
		}
		
		article.setFiles(attachedFileList);
		
		this.dbPort.save(article);		
	}

}
