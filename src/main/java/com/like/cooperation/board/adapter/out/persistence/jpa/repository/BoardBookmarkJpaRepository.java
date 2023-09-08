package com.like.cooperation.board.adapter.out.persistence.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.like.cooperation.board.domain.BoardBookmark;

public interface BoardBookmarkJpaRepository extends JpaRepository<BoardBookmark, Long> {

	List<BoardBookmark> findByUserIdOrderBySeqAsc(String userId);
}
