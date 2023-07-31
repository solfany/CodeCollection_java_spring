package com.cita.board.repository;

import java.util.Date;
import java.util.List;

import com.cita.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
	
	public List<BoardEntity> findTop3ByBoardWriteDateAfterOrderByBoardLikesCountDesc(Date boardWriteDate);

	public List<BoardEntity> findByOrderByBoardWriteDateDesc();
	
	public List<BoardEntity> findByBoardTitleContains(String boardTitle);
}
