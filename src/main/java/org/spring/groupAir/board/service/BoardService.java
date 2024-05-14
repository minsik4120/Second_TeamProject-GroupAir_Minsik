package org.spring.groupAir.board.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.board.dto.BoardDto;
import org.spring.groupAir.board.entity.BoardEntity;
import org.spring.groupAir.board.repository.BoardRepository;
import org.spring.groupAir.board.service.serviceInterface.BoardServiceInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor // 생성자를 자동으로 생성
@Transactional
public class BoardService implements BoardServiceInterface {

  private  final BoardRepository boardRepository;

  @Override
  public void insertBoard(BoardDto boardDto) {


  boardRepository.save(BoardEntity.builder()
          .title(boardDto.getTitle())
          .content(boardDto.getContent())
          .hit(boardDto.getHit())
          .boardAttachFile(boardDto.getBoardAttachFile())
      .build());
  }

  @Override
  public Page<BoardDto> boardSearchPagingList(Pageable pageable, String subject, String search) {



    return null;
  }

  @Override
  public BoardDto detail(Long id) {
    return null;
  }
}
