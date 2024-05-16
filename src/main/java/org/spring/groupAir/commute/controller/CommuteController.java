package org.spring.groupAir.commute.controller;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.commute.dto.CommuteDto;
import org.spring.groupAir.commute.service.CommuteService;
import org.spring.groupAir.department.entity.DepartmentEntity;
import org.spring.groupAir.member.dto.MemberDto;
import org.spring.groupAir.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/commute")
public class CommuteController {

    private final CommuteService commuteService;
    private final MemberService memberService;


    @GetMapping({"", "/", "/index"})
    public String commuteIndex() {

        return "commute/index";
    }

    @GetMapping("/work")
    public String work(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       Model model,
                       @RequestParam(name = "subject", required = false) String subject,
                       @RequestParam(name = "search", required = false) String search) {

        Page<MemberDto> memberDtoPage = memberService.memberList(pageable, subject, search);

        int totalPage = memberDtoPage.getTotalPages();//전체page
        int newPage = memberDtoPage.getNumber();//현재page

        int blockNum = 3; //브라우저에 보이는 페이지 갯수

        int startPage = (int) ((Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPage
            ? (Math.floor(newPage / blockNum) * blockNum) + 1 : totalPage);
        int endPage = (startPage + blockNum) - 1 < totalPage ? (startPage + blockNum) - 1 : totalPage;

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("memberDtoPage", memberDtoPage);

        return "commute/work";
    }

    @GetMapping("/workDetail/{id}")
    public String workDetail(@PathVariable("id") Long id, Model model) {
        List<CommuteDto> commuteDtoList = commuteService.commuteList(id);

        model.addAttribute("commuteDtoList", commuteDtoList);

        return "commute/workDetail";
    }

    @GetMapping("/workIn/{id}")
    public String workIn(@PathVariable("id") Long id) {

        Long memberId = commuteService.workIn(id);

        return "redirect:/commute/workDetail/" + memberId;
    }

    @GetMapping("/workOut/{id}")
    public String workOut(@PathVariable("id") Long id) {

        Long memberId = commuteService.workOut(id);

        return "redirect:/commute/workDetail/" + memberId;
    }

    @GetMapping("/vacation")
    public String vacation() {

        return "commute/vacation";
    }


}
