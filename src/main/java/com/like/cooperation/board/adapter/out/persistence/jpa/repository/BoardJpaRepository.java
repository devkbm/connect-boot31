package com.like.cooperation.board.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.cooperation.board.domain.Board;

@Repository
public interface BoardJpaRepository extends JpaRepository<Board, Long> {	
			
}