package org.spring.groupAir.board.repository;

import org.spring.groupAir.board.entity.BoardReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardReplyRepository  extends JpaRepository<BoardReplyEntity,Long> {


}
