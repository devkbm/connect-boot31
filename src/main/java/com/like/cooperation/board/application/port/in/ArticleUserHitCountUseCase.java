package com.like.cooperation.board.application.port.in;

public interface ArticleUserHitCountUseCase {

	void plusHitCount(Long articleId, String userId);
}
