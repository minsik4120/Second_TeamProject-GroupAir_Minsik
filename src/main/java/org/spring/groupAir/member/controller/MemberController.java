package org.spring.groupAir.member.controller;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.config.MyUserDetailsImpl;
import org.spring.groupAir.member.dto.MemberDto;
import org.spring.groupAir.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/memberJoin")
    public String memberJoin(MemberDto memberDto, Model model) {

        model.addAttribute("memberDto", memberDto);

        return "member/memberJoin";
    }

    @PostMapping("/memberJoin")
    public String memberJoinOk(@Valid MemberDto memberDto,
                               BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "member/memberJoin";
        } else {
            memberService.memberJoin(memberDto);
        }
        return "redirect:/member/memberList";
    }

    @GetMapping("/memberList")
    public String work(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       Model model,
                       @RequestParam(name = "subject", required = false) String subject,
                       @RequestParam(name = "search", required = false) String search){
        Page<MemberDto> memberList = memberService.memberList(pageable, subject, search);

        int totalPage = memberList.getTotalPages();//전체page
        int newPage = memberList.getNumber();//현재page
        Long totalElements = memberList.getTotalElements();//전체 레코드 갯수
        int size = memberList.getSize();//페이지당 보이는 갯수

        int blockNum = 3; //브라우저에 보이는 페이지 갯수

        int startPage = (int) ((Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPage
            ? (Math.floor(newPage / blockNum) * blockNum) + 1 : totalPage);
        int endPage = (startPage + blockNum) - 1 < totalPage ? (startPage + blockNum) - 1 : totalPage;

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("memberList", memberList);

        return "member/memberList";
    }

    @GetMapping("/memberDetail/{id}")
    public ResponseEntity<MemberDto> memberDetail(@PathVariable("id") Long id, Model model){
        MemberDto member= memberService.memberDetail(id);

        if(member!=null) model.addAttribute("key","member");
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping("/memberUpdate/{id}")
    public String memberDetail1(@PathVariable("id") Long id,
                               @AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                               Model model) {
        MemberDto memberDto = memberService.memberDetail(id);
        if (myUserDetails != null) {
            model.addAttribute("myUserDetails", myUserDetails);
        }

        model.addAttribute("memberDto", memberDto);

        return "member/memberDetailUpdate";
    }
    @PostMapping("/memberUpdate")
    public String memberUpdate(MemberDto memberDto) throws IOException {

        memberService.memberUpdate(memberDto);

        return "redirect:/member/memberUpdate/" + memberDto.getId();
    }






}
