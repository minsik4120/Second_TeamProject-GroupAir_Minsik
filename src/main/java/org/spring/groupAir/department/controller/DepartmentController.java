package org.spring.groupAir.department.controller;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.department.dto.DepartmentDto;
import org.spring.groupAir.department.dto.TopDepartmentDto;
import org.spring.groupAir.department.service.DepartmentService;
import org.spring.groupAir.department.service.TopDepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/department")
@SessionAttributes("list")
public class DepartmentController {

    private final TopDepartmentService topDepartmentService;
    private final DepartmentService departmentService;

    @GetMapping("/index")
    public String department() {

        return "department/index";
    }

    @GetMapping("/write")
    public String write(@ModelAttribute("list") List<TopDepartmentDto> list, Model model) {

        return "department/write";
    }

    @PostMapping("/deWrite")
    public String deWrite(DepartmentDto departmentDto) {

        departmentService.write(departmentDto);

        return "redirect:top/deLiList";
    }

    @GetMapping("/detail/{id}")
    public String deDetail(@PathVariable("id") Long id, Model model
            , @ModelAttribute("list") List<TopDepartmentDto> list) {

        DepartmentDto department = departmentService.detail(id);

        model.addAttribute("department", department);

        return "department/detail";
    }

    @PostMapping("/update")
    public String update(DepartmentDto departmentDto) {

        departmentService.update(departmentDto);

        return "redirect:/department/detail/" + departmentDto.getId();
    }

    @GetMapping("/delete/{id}")
    public String deDelete(@PathVariable("id") Long id) {

        departmentService.delete(id);

        String html = "<script>" +
                "alert('부서삭제성공');" +
                "location.href='/top/deLiList';" +
                "</script>";

        return "redirect:/department/top/deLiList";
    }


//    topDepartment ===========================
//    =========================================

    @GetMapping("top/detail/{id}")
    public String topDeDetail(@PathVariable("id") Long id, Model model
            , @ModelAttribute("list") List<TopDepartmentDto> list) {

        TopDepartmentDto topDepartment = topDepartmentService.detail(id);

        model.addAttribute("topDepartment", topDepartment);

        return "department/top/detail";
    }

    @GetMapping("/top/write")
    public String tWrite(@ModelAttribute("list") List<TopDepartmentDto> list, Model model) {

//        model.addAttribute("list", list);

        return "department/top/write";
    }

    @PostMapping("/top/deTopWrite")
    public String deWrite(TopDepartmentDto topDepartmentDto, Model model) {

        topDepartmentService.write(topDepartmentDto);

        return "redirect:/department/top/deLiList";
    }


    @GetMapping("/top/deLiList")
    public String deLiList(TopDepartmentDto topDepartmentDto,
                           HttpSession session, Model model) {

        List<TopDepartmentDto> list = topDepartmentService.List(topDepartmentDto);

        model.addAttribute("list", list);

        return "department/top/deLiList";
    }

    @PostMapping("top/update")
    public String update(TopDepartmentDto topDepartmentDto) {

        topDepartmentService.update(topDepartmentDto);


        return "redirect:/department/top/deLiList";
    }

    @GetMapping("/top/delete/{id}")
    public String topDeDelete(@PathVariable("id") Long id) {

        topDepartmentService.detele(id);

        String html = "<script>" +
                "alert('부서삭제성공');" +
                "location.href='/top/deLiList';" +
                "</script>";

        return "redirect:/department/top/deLiList";
    }


//    페이징용

    @GetMapping("/top/deList")
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

        return "department/top/deList";
    }


}
