package org.spring.groupAir.chat;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.member.dto.MemberDto;
import org.spring.groupAir.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final MemberService memberService;

    @GetMapping("/")
    public String chat(MemberDto memberDto, Model model) {

        return "chat";
    }

}
