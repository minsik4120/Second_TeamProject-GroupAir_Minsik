package org.spring.groupAir.sign.controller;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.sign.dto.SignDto;
import org.spring.groupAir.sign.service.SignFileService;
import org.spring.groupAir.sign.service.SignService;
import org.spring.groupAir.sign.service.SignStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/sign")
@RequiredArgsConstructor
public class SignController {

//    @Autowired
//    private SignService signService;
//
//    @Autowired
//    private SignFileService signFileService;
//
//    @Autowired
//    private SignStatusService signStatusService;
//
//    @GetMapping({"", "/index"})
//    public String index(@ModelAttribute("signList") List<SignDto> signList) {
//
//        return "sign/index";
//
//    }
//}


    @GetMapping({"", "/index"})
    public String index() {

        return "sign/index";
    }


}





















