package org.spring.groupAir.board.controller;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.board.dto.BoardDto;
import org.spring.groupAir.board.service.BoardService;
import org.spring.groupAir.config.MyUserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

  private final BoardService boardService;


  @GetMapping("/write")
  public String write() {   //

    return "board/write";

  }


  //  로그인 한 회원 만 들어 갈 수 있게 하는 거
/*  @GetMapping("/write")
  public String write(@AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                      BoardDto boardDto,
                      Model model){
    model.addAttribute("memberId"
        , myUserDetails.getMemberEntity().getId());

    return "board/write";
  }

  @PostMapping("/write") // 유효성 검사 폼 데이터를 처리 Java 객체에 매핑
  public String writeOk(@Valid BoardDto boardDto ,
                          BindingResult bindingResult){
    if (bindingResult.hasErrors()){
      return "board/write";
    }
    boardService.insertBoard(boardDto);

    return "redirect:/board/boardList";
  }*/



}
