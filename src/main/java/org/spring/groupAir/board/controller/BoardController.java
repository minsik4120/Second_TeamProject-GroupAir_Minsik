package org.spring.groupAir.board.controller;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.board.dto.BoardDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

  \
  @PostMapping("/write") // 유효성 검사 폼 데이터를 처리 Java 객체에 매핑
  public String writeOk(@Valid BoardDto boardDto ,
                        BindingResult bindingResult){

    return "board/write";
  }
}
