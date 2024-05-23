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

    @GetMapping({"/", "/index"})
    public String department() {

        return "department/top/deList";
    }

    @GetMapping("/write")
    public String write(@ModelAttribute("list") List<TopDepartmentDto> list, Model model) {

        return "department/write";
    }

    @PostMapping("/deWrite")
    public String deWrite(DepartmentDto departmentDto) {

        departmentService.write(departmentDto);

        return "redirect:top/deList";
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

        return "redirect:/department/top/deList";
    }

    @GetMapping("/delete/{id}")
    public String deDelete(@PathVariable("id") Long id) {

        departmentService.delete(id);

        String html = "<script>" +
                "alert('부서삭제성공');" +
                "location.href='/top/deList';" +
                "</script>";

        return "redirect:/department/top/deList";
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

        return "redirect:/department/top/deList";
    }


    @GetMapping("/top/deList")
    public String deList(TopDepartmentDto topDepartmentDto,
                         HttpSession session, Model model) {

        List<TopDepartmentDto> list = topDepartmentService.List(topDepartmentDto);

        model.addAttribute("list", list);

        return "department/top/deList";
    }

    @PostMapping("top/update")
    public String update(TopDepartmentDto topDepartmentDto) {

        topDepartmentService.update(topDepartmentDto);


        return "redirect:/department/top/deList";
    }

    @GetMapping("/top/delete/{id}")
    public String topDeDelete(@PathVariable("id") Long id) {

        topDepartmentService.detele(id);

        String html = "<script>" +
                "alert('부서삭제성공');" +
                "location.href='/top/deList';" +
                "</script>";

        return "redirect:/department/top/deList";
    }


    // 회원가입 부서가져오기
    @GetMapping("/list")
    @ResponseBody
    public List<TopDepartmentDto> deList(TopDepartmentDto topDepartmentDto) {
        return topDepartmentService.List(topDepartmentDto);
    }

    @GetMapping("/subDepartments")
    @ResponseBody
    public List<DepartmentDto> getSubDepartments(@RequestParam("topDepartmentId") Long topDepartmentId) {
        // 선택된 상위 부서에 해당하는 하위 부서 목록을 가져옴
        return departmentService.getSubDepartments(topDepartmentId);
    }


}
