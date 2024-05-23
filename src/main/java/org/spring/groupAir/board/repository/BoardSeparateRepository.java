package org.spring.groupAir.board.repository;

import org.spring.groupAir.board.entity.BoardEntity;
import org.spring.groupAir.board.entity.BoardSeparateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardSeparateRepository extends JpaRepository<BoardSeparateEntity, Long> {


}
