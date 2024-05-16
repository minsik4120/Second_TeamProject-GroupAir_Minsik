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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/commute")
public class CommuteController {

    private final CommuteService commuteService;
    private final MemberService memberService;


    @GetMapping("/index")
    public String index(){

        return "commute/index";
    }
    @GetMapping("/work")
    public String work(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       Model model,
                       @RequestParam(name = "subject", required = false) String subject,
                       @RequestParam(name = "search", required = false) String search){
        Page<CommuteDto> commuteDtoPage = commuteService.commuteList(pageable, subject, search);

        int totalPage = commuteDtoPage.getTotalPages();//전체page
        int newPage = commuteDtoPage.getNumber();//현재page
        Long totalElements = commuteDtoPage.getTotalElements();//전체 레코드 갯수
        int size = commuteDtoPage.getSize();//페이지당 보이는 갯수

        int blockNum = 3; //브라우저에 보이는 페이지 갯수

        int startPage = (int) ((Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPage
            ? (Math.floor(newPage / blockNum) * blockNum) + 1 : totalPage);
        int endPage = (startPage + blockNum) - 1 < totalPage ? (startPage + blockNum) - 1 : totalPage;

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("commuteDtoPage", commuteDtoPage);

        return "commute/work";
    }

    @GetMapping("/vacation")
    public String vacation(){

        return "commute/vacation";
    }



}
