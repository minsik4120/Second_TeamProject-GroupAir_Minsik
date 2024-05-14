package org.spring.groupAir.board.service.serviceInterface;

import org.spring.groupAir.board.dto.BoardReplyDto;

import java.util.List;

public interface BoardReplyServiceInterface {

  List<BoardReplyDto> repliyList(Long id);
}
