package org.spring.groupAir.board.controller;


import lombok.RequiredArgsConstructor;
import org.spring.groupAir.board.dto.BoardReplyDto;
import org.spring.groupAir.board.repository.BoardRepository;
import org.spring.groupAir.board.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/reply")
public class ReplyController {

  private final ReplyService replyService;


  @PostMapping("/replyWrite")
  public String replyWrite(BoardReplyDto boardReplyDto){


    replyService.insertReply(boardReplyDto);

    return "redirect:board/detail"+ boardReplyDto.getBoardId();

  }

/*  @GetMapping("replyList{id}")
  @ResponseBody
  public List<BoardReplyDto> replyDtoList(@PathVariable("id")Long id) {

    List<BoardReplyDto> replyDtoList=replyService.ajaxReplyList(id);

    return replyDtoList;
  }*/


  @PostMapping("/ajaxWrite")
  @ResponseBody
  public BoardReplyDto ajaxWrite(BoardReplyDto boardReplyDto) {
    BoardReplyDto reply = replyService.ajaxInsert(boardReplyDto);

    return reply;
  }

  @GetMapping("/replyList{id}")
  @ResponseBody
  public List<BoardReplyDto> replyList(@PathVariable("id")Long id) {

    List<BoardReplyDto> replyList = replyService.ajaxReplyList(id);
    return replyList;

  }

}
