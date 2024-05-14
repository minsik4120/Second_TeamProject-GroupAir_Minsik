package org.spring.groupAir.board.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.board.dto.BoardReplyDto;
import org.spring.groupAir.board.service.serviceInterface.BoardReplyServiceInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardReplyService implements BoardReplyServiceInterface {

  @Override
  public List<BoardReplyDto> repliyList(Long id) {
    return null;
  }
}
