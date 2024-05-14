package org.spring.groupAir.department.controller;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.department.dto.TopDepartmentDto;
import org.spring.groupAir.department.service.DepartmentService;
import org.spring.groupAir.department.service.TopDepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/topDepartment")
public class TopDepartmentController {

    private final TopDepartmentService topDepartmentService;

    @GetMapping("/index")
    public String topDepartment() {

        return "topDepartment/index";
    }

    @GetMapping("/make")
    public String makeTopDepartment(TopDepartmentDto topDepartmentDto) {

        return "topDepartment/make";
    }

    @GetMapping("/deList")
    public String deList(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                         Model model) {

        Page<TopDepartmentDto> pageList = topDepartmentService.pageList(pageable);

        int totalPages = pageList.getTotalPages(); //전체 페이지
        int nowPage = pageList.getNumber(); //현재 페이지
        long totalElements = pageList.getTotalElements(); //전체 레코드 개수
        int size = pageList.getSize(); //페이지 당 보이는 개수

        int blockNum = 3; // 브라우저에 보이는 페이지 번호

        int startPage = (int) ((Math.floor(nowPage / blockNum) * blockNum) + 1 <= totalPages ? (Math.floor(nowPage / blockNum) * blockNum) + 1 : totalPages);
        int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("pageList", pageList);

        return "topDepartment/deList";
    }

    @GetMapping("/deLiList")
    public String deLiList(TopDepartmentDto topDepartmentDto, Model model) {

        List<TopDepartmentDto> list = topDepartmentService.List(topDepartmentDto);

        model.addAttribute("list", list);

        return "topDepartment/deLiList";
    }

    @PostMapping("/write")
    public String deWrite(TopDepartmentDto topDepartmentDto, Model model) {

        topDepartmentService.write(topDepartmentDto);

        return "redirect:deLiList";
    }

}
