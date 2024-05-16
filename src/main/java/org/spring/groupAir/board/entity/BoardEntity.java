package org.spring.groupAir.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.groupAir.board.dto.BoardDto;
import org.spring.groupAir.contraint.BaseTimeEntity;
import org.spring.groupAir.member.entity.MemberEntity;
import org.spring.groupAir.schedule.entity.ScheduleEntity;
import org.spring.groupAir.schedule.entity.ScheduleSeparateEntity;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
@Entity
@Table(name = "board")
public class BoardEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int hit;

    @Column(nullable = false)
    private int boardAttachFile; //게시글 작성시 파일이 존재하면 1, 없으면 0

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private MemberEntity memberEntity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardSeparate_id")
    private BoardSeparateEntity boardSeparateEntity;

    @OneToMany(mappedBy = "boardEntity"
        , fetch = FetchType.LAZY
        , cascade = CascadeType.REMOVE)
    private List<BoardReplyEntity> boardReplyEntityList;

    @OneToMany(mappedBy = "boardEntity"
        , fetch = FetchType.LAZY
        , cascade = CascadeType.REMOVE)
    private List<BoardFileEntity> boardFileEntityList;


  public static BoardEntity toInsertBoardEntity(BoardDto boardDto) {


      BoardEntity boardEntity = new BoardEntity();
      boardEntity.setTitle(boardDto.getTitle());
      boardEntity.setContent(boardDto.getContent());
      boardEntity.setWriter(boardDto.getWriter());
      boardEntity.setBoardAttachFile(0);
      boardEntity.setMemberEntity(boardEntity.getMemberEntity());

      return  boardEntity;
  }


    public static BoardEntity toInsertFileBoardEntity(BoardDto boardDto) {
      BoardEntity boardEntity = new BoardEntity();
      boardEntity.setTitle(boardDto.getTitle());
      boardEntity.setContent(boardDto.getContent());
      boardEntity.setWriter(boardDto.getWriter());
      boardEntity.setBoardAttachFile(1);
      boardEntity.setMemberEntity(boardDto.getMemberEntity());
      return boardEntity;
    }
}
