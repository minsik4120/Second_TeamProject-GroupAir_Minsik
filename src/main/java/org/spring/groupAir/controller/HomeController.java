package org.spring.groupAir.controller;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.config.MyUserDetailsImpl;
import org.spring.groupAir.member.dto.MemberDto;
import org.spring.groupAir.member.service.MemberService;
import org.spring.groupAir.schedule.dto.ScheduleDto;
import org.spring.groupAir.schedule.entity.ScheduleEntity;
import org.spring.groupAir.schedule.service.ScheduleService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final ScheduleService scheduleService;

    @GetMapping({"","/","/index"})
    public String index(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception, Model model){

        model.addAttribute("exception", exception);
        return "index";
    }

    @GetMapping("/role/admin")
    public String adminPage(){

        return "role/admin";
    }
    @GetMapping("/role/manager")
    public String managerPage(){

        return "role/manager";
    }
    @GetMapping("/role/member")
    public String memberPage(){

        return "role/member";
    }
}
