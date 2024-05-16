package org.spring.groupAir.board.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.board.dto.BoardDto;
import org.spring.groupAir.board.dto.BoardFileDto;
import org.spring.groupAir.board.entity.BoardEntity;
import org.spring.groupAir.board.entity.BoardFileEntity;
import org.spring.groupAir.board.repository.BoardFileRepository;
import org.spring.groupAir.board.repository.BoardRepository;
import org.spring.groupAir.board.service.serviceInterface.BoardServiceInterface;
import org.spring.groupAir.member.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor // 생성자를 자동으로 생성
@Transactional
public class BoardService implements BoardServiceInterface {

  private  final BoardRepository boardRepository;
  private final BoardFileRepository fileRepository;

  @Override
  public void insertBoard(BoardDto boardDto) throws IOException {



/*  boardRepository.save(BoardEntity.builder()
          .title(boardDto.getTitle())
          .content(boardDto.getContent())
          .writer(boardDto.getWriter())
          .hit(boardDto.getHit())
          .boardAttachFile(boardDto.getBoardAttachFile())
      .build());

  */


      // 파일이 없는 경우
      //dto->entity
    if(boardDto.getBoardFile().isEmpty()) {

      boardDto.setMemberEntity(MemberEntity.builder()
          .id(boardDto.getMemberId())
          .build());

      BoardEntity boardEntity = BoardEntity.toInsertBoardEntity(boardDto);

      boardRepository.save(boardEntity);

    } else {

      // 파일이 있는 경우
      // 로컬에 실제 파일을 저장 시킴
      MultipartFile boardFile = boardDto.getBoardFile(); // 실제 파일

      String oldFileName = boardFile.getOriginalFilename(); //   원본 이미지 파일 이름
      UUID uuid = UUID.randomUUID();// 랜덤하게
      String newFileName = uuid + "_" + oldFileName;; // 램덤하게 이름 들어감
      String fileSavePath = "C:/groupAir/" + newFileName; // 여기 경로로 저장됨
      boardFile.transferTo(new File(fileSavePath)); // IoException


      // 1.  게시글
      boardDto.setMemberEntity(MemberEntity.builder()
              .id(boardDto.getMemberId())
              .build());
      BoardEntity boardEntity = BoardEntity.toInsertFileBoardEntity(boardDto);
      Long id = boardRepository.save(boardEntity).getId();

      Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
      if(optionalBoardEntity.isPresent()) {
        //  게시글 0
        BoardEntity boardEntity1 = optionalBoardEntity.get(); // Entity
        // 게시글에 정상 저장되면 -> 파일 Entity 저장

        BoardFileDto fileDto = BoardFileDto.builder()
            .boardOldFile(oldFileName)
            .boardNewFile(newFileName)
            .boardEntity(boardEntity1)
            .build();


        BoardFileEntity fileEntity = BoardFileEntity.toInsertFile(fileDto);
        fileRepository.save(fileEntity);
      } else  {

        throw  new  IllegalArgumentException("아이디가 없습니다.");
      }


    }



  }

  @Override
  public Page<BoardDto> boardSearchPagingList(Pageable pageable, String subject, String search) {
    Page<BoardEntity> boardEntities = null;

    if (search == null || subject == null) {
      boardEntities = boardRepository.findAll(pageable);

    } else  {

      if (subject.equals("title")) {
        boardEntities  = boardRepository.findByTitleContaining(pageable, search);

      } else if (subject.equals("content")) {
        boardEntities = boardRepository.findByContentContaining(pageable, search);
      } else if (subject.equals("writer")) {
        boardEntities = boardRepository.findByWriterContaining(pageable,search);
      } else  {
        boardEntities = boardRepository.findAll(pageable);
      }
    }
    Page<BoardDto> boardDtos = boardEntities.map(BoardDto::toSelectBoardDto);

    return boardDtos;
  }

  @Override
  public BoardDto detail(Long id) {
    return null;
  }
}
