package com.like.cooperation.board.adapter.out.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.cooperation.board.adapter.out.persistence.jpa.repository.ArticleJpaRepository;
import com.like.cooperation.board.adapter.out.persistence.jpa.repository.BoardJpaRepository;
import com.like.cooperation.board.application.dto.ArticleSaveDTO;
import com.like.cooperation.board.application.dto.ArticleSaveMultipartDTO;
import com.like.cooperation.board.application.dto.ResponseArticle;
import com.like.cooperation.board.application.port.out.ArticleDeleteDbPort;
import com.like.cooperation.board.application.port.out.ArticleSaveDbPort;
import com.like.cooperation.board.application.port.out.ArticleSelectDbPort;
import com.like.cooperation.board.domain.Article;
import com.like.cooperation.board.domain.ArticleAttachedFile;
import com.like.cooperation.board.domain.AttachedFileConverter;
import com.like.cooperation.board.domain.Board;
import com.like.system.core.util.SessionUtil;
import com.like.system.file.application.service.FileService;
import com.like.system.file.domain.FileInfo;

@Repository
public class ArticleDbAdapter implements ArticleSelectDbPort, ArticleSaveDbPort, ArticleDeleteDbPort {
	ArticleJpaRepository repository;
	BoardJpaRepository boardRepository;
	FileService fileService;
	
	ArticleDbAdapter(ArticleJpaRepository repository
			        ,BoardJpaRepository boardRepository
			        ,FileService fileService) {
		this.repository = repository;
		this.boardRepository = boardRepository;
		this.fileService = fileService;
	}

	@Override
	public Article select(Long articleId) {
		return this.repository.findById(articleId).orElse(null);
	}
	
	@Override
	public ResponseArticle selectDTO(Long articleId) {		
		Article entity = this.repository.findById(articleId).orElse(null); 
		return ResponseArticle.toDTO(entity);
	}

	@Override
	public void save(ArticleSaveDTO dto) {
		Board board = boardRepository.findById(dto.boardId()).orElseThrow(() -> new IllegalArgumentException("존재 하지 않은 게시판입니다."));
		List<FileInfo> fileInfoList = Collections.emptyList();
		List<ArticleAttachedFile> attachedFileList = Collections.emptyList();
		
		Article entity = dto.newArticle(board); 
		
		// 2. 저장된 파일 리스트를 조회한다.
		// 3. FileInfo를 AttachedFile로 변환한다.
		if (dto.attachFile() != null) {
			fileInfoList = fileService.getFileInfoList(dto.attachFile());
			
			attachedFileList = AttachedFileConverter.convert(entity, fileInfoList);				
			if (!attachedFileList.isEmpty()) entity.setFiles(attachedFileList);
		}
		
		this.repository.save(entity);
	}
	
	@Override
	public void save(ArticleSaveMultipartDTO dto) {
		Board board = boardRepository.findById(dto.boardId()).orElseThrow(() -> new IllegalArgumentException("존재 하지 않은 게시판입니다."));
		
		List<FileInfo> fileInfoList = null;
		List<ArticleAttachedFile> attachedFileList = null;					
		
		Article article = dto.newArticle(board);
		
		// 첨부파일 저장
		if (!dto.file().isEmpty()) {		
			String userId = SessionUtil.getUserId();
			try {
				fileInfoList = fileService.uploadFile(dto.file(), userId, "board");
			} catch (FileNotFoundException e) {
				throw new IllegalStateException("파일 업로드중 오류가 발생했습니다.", e);
			} catch (IOException e) {
				throw new IllegalStateException("파일 업로드중 오류가 발생했습니다.", e);
			}
			attachedFileList = AttachedFileConverter.convert(article, fileInfoList);
		}
		
		article.setFiles(attachedFileList);
		
		this.repository.save(article);		
	}
	
	@Override
	public void delete(Long articleId) {
		this.repository.deleteById(articleId);
		
	}	
	
}
