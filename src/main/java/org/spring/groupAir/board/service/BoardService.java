package org.spring.groupAir.board.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.board.dto.BoardDto;
import org.spring.groupAir.board.dto.BoardFileDto;
import org.spring.groupAir.board.entity.BoardEntity;
import org.spring.groupAir.board.entity.BoardFileEntity;
import org.spring.groupAir.board.entity.BoardSeparateEntity;
import org.spring.groupAir.board.repository.BoardFileRepository;
import org.spring.groupAir.board.repository.BoardRepository;
import org.spring.groupAir.board.repository.BoardSeparateRepository;
import org.spring.groupAir.board.service.serviceInterface.BoardServiceInterface;
import org.spring.groupAir.member.entity.MemberEntity;
import org.spring.groupAir.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor // 생성자를 자동으로 생성
@Transactional
public class BoardService implements BoardServiceInterface {

  private  final BoardRepository boardRepository;
  private final BoardFileRepository fileRepository;
  private final BoardSeparateRepository boardSeparateRepository;
  private final MemberRepository memberRepository;



  @Override
  public void insertBoard(BoardDto boardDto) throws IOException {
      // 파일이 없는 경우
      //dto->entity
    if(boardDto.getBoardFile().isEmpty()) {

      boardDto.setMemberEntity(MemberEntity.builder()
          .id(boardDto.getMemberId())
          .build());

      System.out.println(">>>>>" + boardDto.getBoardSeparateId());

      BoardSeparateEntity boardSeparateEntity = boardSeparateRepository.findById(boardDto.getBoardSeparateId()).orElseThrow(IllegalArgumentException::new);



      boardDto.setBoardSeparateEntity(BoardSeparateEntity.builder().id(boardDto.getBoardSeparateId())
          .boardSeparateName(boardSeparateEntity.getBoardSeparateName()).build());

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
    /*  boardDto.setMemberEntity(MemberEntity.builder()
              .id(boardDto.getMemberId())
              .build());*/

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
      }
      else  {

        throw  new  IllegalArgumentException("아이디가 없습니다.");
      }
    }
  }

  @Transactional
  @Override
  public void update(BoardDto boardDto) throws IOException {

    BoardEntity boardEntity = boardRepository.findById(boardDto.getId())
        .orElseThrow(()-> new IllegalArgumentException("수정할 게시글이 존재하지 않습니다."));
    // 파일 체크

    System.out.println("file1");

    Optional<BoardFileEntity> optionalBoardFileEntity =
        fileRepository.findByBoardEntityId(boardDto.getId());

    System.out.println("file2");
    // 파일이 있으면 파일 기존 파일 삭제

    if(optionalBoardFileEntity.isPresent()) {

      String fileNewName = optionalBoardFileEntity.get().getBoardNewFile(); // DB 저장 파일 이름
      String filePath = "C:/groupAir/" + fileNewName;
      File deleteFile = new File(filePath);

      if(deleteFile.exists()) {
        deleteFile.delete(); // 파일 삭제 (로컬)
        System.out.println("파일을 삭제하였습니다");
      }else {
        System.out.println("파일이 존재하지 않습니다");
      }


      fileRepository.delete(optionalBoardFileEntity.get()); // 파일 테이블 레코드 삭제
    }
//
//    // 게시글 수정
//    Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardDto.getId());
//
//    boardDto.setMemberEntity(MemberEntity.builder().id(boardDto.getMemberId()).build());

    System.out.println(">>>>>"+boardDto.getBoardFile());

    if(boardDto.getBoardFile().isEmpty()){


      System.out.println(boardDto);

      // 파일이 없는 경우
      boardEntity = BoardEntity.toUpdateEntity(boardDto);

      System.out.println(boardDto+" <<< ");

      boardRepository.save(boardEntity);

    }else{

      // 파일이 있는 경우
      MultipartFile boardFile = boardDto.getBoardFile();
      String fileOldName = boardFile.getOriginalFilename();
      UUID uuid = UUID.randomUUID();
      String fileNewName = uuid + "_" + fileOldName;

      // 로컬 (서버)에 새로운 이름으로 저장
      String savePath = "C:/groupAir/" + fileNewName;
      boardFile.transferTo(new File(savePath));

      // 게시판
      boardEntity = BoardEntity.toUpdateFileBoardEntity(boardDto);
      boardRepository.save(boardEntity);

      // 파일 저장 (table)
      BoardFileEntity bFileEntity = BoardFileEntity.builder()
          .boardEntity(boardEntity)
          .boardNewFile(fileNewName)
          .boardOldFile(fileOldName)
          .build();

      Long fileId = fileRepository.save(bFileEntity).getId();

      fileRepository.findById(fileId).orElseThrow(()-> {
        throw  new IllegalArgumentException("파일 등록에 실패 하였습니다.");
      });
    }

    // 게시글 수정 확인
    boardRepository.findById(boardDto.getId()).orElseThrow(()->{
      throw new IllegalArgumentException("게시글 수정에 실해하였습니다.");
    });

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
    updateHit(id);

    Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);


    if (optionalBoardEntity.isPresent()) {

      BoardEntity boardEntity = optionalBoardEntity.get(); //
      BoardDto boardDto = BoardDto.toSelectBoardDto(boardEntity);

    return boardDto;
    }
    throw new IllegalArgumentException("아이디가 Fail!");
  }

  private void updateHit(Long id) {
    boardRepository.updateHitById(id);

  }






  @Override
  public void deleteBoard(Long id) {

    BoardEntity boardEntity = boardRepository.findById(id).orElseThrow(()->{

      throw new IllegalArgumentException("삭제할 게시글이 존재하지 않습니다.");
    });

    Optional<BoardFileEntity> optionalBoardFileEntity = fileRepository.findByBoardEntityId(boardEntity.getId());

    if (optionalBoardFileEntity.isPresent()) {
      String fileNewName = optionalBoardFileEntity.get().getBoardNewFile();
      String filePath = "C:/groupAir/" + fileNewName;
      File deleteFile = new File(filePath);

      if (deleteFile.exists()) {
        deleteFile.delete();
        System.out.println("파일을 삭제하였습니다.");
      } else {
        System.out.println("파일이 존재하지 않습니다.");
      }
    fileRepository.delete(optionalBoardFileEntity.get()); // 파일 테이블 레코드 삭제ㅐ
    }

  boardRepository.delete(boardEntity);
  }

  @Override
  public List<BoardEntity> getBoardsBySeparateId(Long boardSeparateId) {



    return boardRepository.findByBoardSeparateEntityId(boardSeparateId);
  }

 /* @Override
  public BoardSeparateEntity getBoardSeparateById(Long boardSeparateId) {


    return boardRepository.findByBoardSeparateId(boardSeparateId);
  }
*/

}
