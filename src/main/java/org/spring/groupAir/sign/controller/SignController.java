package org.spring.groupAir.sign.controller;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.sign.dto.SignDto;
import org.spring.groupAir.sign.service.SignService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
@RequestMapping("/sign")
public class SignController {

    private final SignService signService;

    @GetMapping({"", "/index"})
    public String index() {

        return "sign/index";
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("signDto", new SignDto());
        return "sign/write";
    }

    @PostMapping("/write")
    public String writeOk(@Valid SignDto signDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "sign/write";
        }
        signService.write(signDto);
        return "redirect:/sign/signList";

    }

    @GetMapping("/signList")
    public String signList(@PageableDefault(page = 0, size = 8, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                           @RequestParam(name = "subject", required = false) String subject,
                           @RequestParam(name = "search", required = false) String search,
                           Model model) {

        Page<SignDto> signList = signService.signSearchPagingList(pageable, subject, search);

        int totalPages = signList.getTotalPages();
        int newPage = signList.getNumber();
        long totalElements = signList.getTotalElements();
        int size = signList.getSize();

        int blockNum = 3;
        int startPage = Math.max(1, Math.min(newPage / blockNum * blockNum + 1, totalPages));


        int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;


        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("signList", signList);


        return "sign/signList";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        SignDto sign = signService.signOne(id);
        if (sign != null) {
            model.addAttribute("sign", sign);
        }
        return "sign/detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        signService.deleteSign(id);
        return "redirect:/sign/signList";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute SignDto signDto){
        signService.update(signDto);
        return "redirect:/sign/detail/" + signDto.getId();
    }






}















