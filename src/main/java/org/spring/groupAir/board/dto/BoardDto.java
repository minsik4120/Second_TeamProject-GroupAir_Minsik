package org.spring.groupAir.board.dto;

import lombok.*;
import org.spring.groupAir.board.entity.BoardFileEntity;
import org.spring.groupAir.board.entity.BoardReplyEntity;
import org.spring.groupAir.board.entity.BoardSeparateEntity;
import org.spring.groupAir.member.entity.MemberEntity;

import java.util.List;
@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
  private Long id;


  public String title;


  public String content;


  private int hit;


  private int boardAttachFile; //게시글 작성시 파일이 존재하면 1, 없으면 0


  private MemberEntity memberEntity;


  private BoardSeparateEntity boardSeparateEntity;


  private List<BoardReplyEntity> boardReplyEntityList;


  private List<BoardFileEntity> boardFileEntityList;


}
