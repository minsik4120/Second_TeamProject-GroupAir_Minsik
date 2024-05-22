package org.spring.groupAir.airplane.controller;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.airplane.dto.AirplaneDto;
import org.spring.groupAir.airplane.service.AirplaneService;
import org.spring.groupAir.salary.service.SalaryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airplane")
@RequiredArgsConstructor
public class AirPlaneController {

    private final AirplaneService airplaneService;

    @GetMapping({"", "/", "/index"})
    public String airplaneIndex(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                Model model,
                                @RequestParam(name = "subject", required = false) String subject,
                                @RequestParam(name = "search", required = false) String search) {

        Page<AirplaneDto> airplaneDtoPage = airplaneService.allAirplane(pageable, subject, search);

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
    public String airplaneRegistration() {

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
}
