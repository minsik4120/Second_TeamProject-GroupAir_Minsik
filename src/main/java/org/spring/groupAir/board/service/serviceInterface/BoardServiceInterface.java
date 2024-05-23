package org.spring.groupAir.board.service.serviceInterface;

import org.spring.groupAir.board.dto.BoardDto;
import org.spring.groupAir.board.entity.BoardEntity;
import org.spring.groupAir.board.entity.BoardSeparateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface BoardServiceInterface {
  void insertBoard(BoardDto boardDto) throws IOException;


  Page<BoardDto> boardSearchPagingList(Pageable pageable, String subject, String search);

  BoardDto detail(Long id);


  void update(BoardDto boardDto) throws IOException;

  void deleteBoard(Long id);


  List<BoardEntity> getBoardsBySeparateId(Long boardSeparateId);

/*  BoardSeparateEntity getBoardSeparateById(Long boardSeparateId);*/
}
