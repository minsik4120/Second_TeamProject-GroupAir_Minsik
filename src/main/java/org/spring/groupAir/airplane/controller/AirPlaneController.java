package org.spring.groupAir.airplane.controller;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.airplane.dto.AirplaneDto;
import org.spring.groupAir.airplane.service.AirplaneService;
import org.spring.groupAir.member.dto.MemberDto;
import org.spring.groupAir.member.entity.MemberEntity;
import org.spring.groupAir.member.service.MemberService;
import org.spring.groupAir.salary.service.SalaryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/airplane")
@RequiredArgsConstructor
public class AirPlaneController {

    private final AirplaneService airplaneService;
    private final MemberService memberService;

    @GetMapping({"", "/", "/index"})
    public String airplaneIndex(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                Model model,
                                @RequestParam(name = "subject", required = false) String subject,
                                @RequestParam(name = "search", required = false) String search) {

        Page<AirplaneDto> airplaneDtoPage = airplaneService.allAirplane(pageable, subject, search);

        airplaneService.deleteOverTimeAirplane();

        int totalPage = airplaneDtoPage.getTotalPages();//전체page
        int newPage = airplaneDtoPage.getNumber();//현재page
        int blockNum = 3; //브라우저에 보이는 페이지 갯수

        int startPage = (int) ((Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPage
            ? (Math.floor(newPage / blockNum) * blockNum) + 1 : totalPage);
        int endPage = (startPage + blockNum) - 1 < totalPage ? (startPage + blockNum) - 1 : totalPage;

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("airplaneDtoPage", airplaneDtoPage);

        return "airplane/index";
    }

    @GetMapping("/registration")
    public String airplaneRegistration(Model model) {

        List<MemberDto> memberDtoList = memberService.selectPilot();
        model.addAttribute("memberDtoList", memberDtoList);

        return "airplane/registration";
    }

    @PostMapping("/registration")
    public String airplaneRegistrationOk(AirplaneDto airplaneDto) {

        airplaneService.addAirplane(airplaneDto);

        return "redirect:/airplane/index";
    }

    @GetMapping("/detail/{id}")
    public String airplaneDetail(@PathVariable("id") Long id, Model model) {

        AirplaneDto airplaneDto = airplaneService.airplaneDetail(id);

        model.addAttribute("airplaneDto", airplaneDto);

        return "airplane/detail";
    }

    @GetMapping("/delete/{id}")
    public String airplaneDelete(@PathVariable("id") Long id) {

        airplaneService.airplaneDelete(id);

        return "redirect:/airplane/index";
    }

    @GetMapping("/pilot")
    public String pilotList(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                            Model model) {

        Page<MemberDto> memberDtoPage = memberService.pageSelectPilot(pageable);

        int totalPage = memberDtoPage.getTotalPages();//전체page
        int newPage = memberDtoPage.getNumber();//현재page
        int blockNum = 3; //브라우저에 보이는 페이지 갯수

        int startPage = (int) ((Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPage
            ? (Math.floor(newPage / blockNum) * blockNum) + 1 : totalPage);
        int endPage = (startPage + blockNum) - 1 < totalPage ? (startPage + blockNum) - 1 : totalPage;

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("memberDtoPage", memberDtoPage);


        return "airplane/pilot";
    }

    @GetMapping("/myAirplane/{id}")
    public String myAirplane(@PathVariable("id") Long id,
                             @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                             Model model) {

        Page<AirplaneDto> airplaneDtoPage = airplaneService.myAirplane(pageable, id);
        Page<AirplaneDto> todayAirplaneDtoPage = airplaneService.todayMyAirplane(pageable, id);
        int totalPage = airplaneDtoPage.getTotalPages();//전체page
        int newPage = airplaneDtoPage.getNumber();//현재page
        int blockNum = 3; //브라우저에 보이는 페이지 갯수

        int startPage = (int) ((Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPage
            ? (Math.floor(newPage / blockNum) * blockNum) + 1 : totalPage);
        int endPage = (startPage + blockNum) - 1 < totalPage ? (startPage + blockNum) - 1 : totalPage;

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("airplaneDtoPage", airplaneDtoPage);
        model.addAttribute("todayAirplaneDtoPage", todayAirplaneDtoPage);
        model.addAttribute("id", id);


        return "airplane/myAirplane";
    }
}
