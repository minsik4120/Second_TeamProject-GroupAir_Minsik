package org.spring.groupAir.sign.controller;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.config.MyUserDetailsImpl;
import org.spring.groupAir.member.service.memberServiceInterface.MemberServiceInterface;
import org.spring.groupAir.sign.dto.SignDto;
import org.spring.groupAir.sign.service.SignService;
import org.spring.groupAir.sign.service.serviceInterface.SignServiceInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/sign")
public class SignController {

    private final SignService signService;
    private final SignServiceInterface signServiceInterface;
    private final MemberServiceInterface memberServiceInterface;

    @GetMapping({"", "/index"})
    public String index() {

        return "sign/index";
    }
//기존write
//    @GetMapping("/write")
//    public String write(Model model) {
//        model.addAttribute("signDto", new SignDto());
//        return "sign/write";
//    }


    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("signDto", new SignDto());
        model.addAttribute("members", memberServiceInterface.memberList(Pageable.unpaged(), null, null));


        return "sign/write";
    }


    // 현재 사용자의 이름을 기반으로 해당 사용자만 게시글 조회
//        Page<SignDto> signList = signService.signListByAuthor(currentUserName, Pageable.unpaged());
//        model.addAttribute("signList", signList);
//
//        model.addAttribute("members", memberServiceInterface.memberList(Pageable.unpaged(), null, null));
//
//


//        return "sign/write"; // 모든 회원 정보를 가져와서 모델에 추가
//


    private boolean checkUserRole(String name, String role) {

        return "approver".equalsIgnoreCase("lastApprover");
    }

    @PostMapping("/write")
    public String writeOk(@Valid SignDto signDto, BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return "sign/write";
        }
//        signService.write(signDto);

        signService.insertSign(signDto);
        return "redirect:/sign/signList";

    }


    private boolean isApprover(String userName) {
        // 사용자 이름을 기준으로 승인자 여부를 확인하는 로직을 작성합니다.
        // 예를 들어, 사용자 이름이 "admin"이거나 "approver"라면 승인자로 간주합니다.
        return "admin".equalsIgnoreCase(userName) || "lastApprover".equalsIgnoreCase(userName);
    }


//    @GetMapping("/signList")
//    public String signList(@PageableDefault(page = 0, size = 8, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
//                           @RequestParam(name = "subject", required = false) String subject,
//                           @RequestParam(name = "search", required = false) String search,
//                           Model model) {
//
//        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        //Page<SignDto> signList = signService.signSearchPagingList(pageable, subject, search);
//
//        //Page<SignDto> signList = signService.signListByAuthor(currentUserName, pageable);
//
//        boolean isApprover = isApprover(currentUserName);
//
//        if (isApprover) {
//            // 승인자인 경우에만 게시글 목록을 가져옴
//            Page<SignDto> signList = signService.signSearchPagingList(pageable, subject, search);
//
//
//            int totalPages = signList.getTotalPages();
//            int newPage = signList.getNumber();
//            long totalElements = signList.getTotalElements();
//            int size = signList.getSize();
//
//            int blockNum = 3;
//            int startPage = Math.max(1, Math.min(newPage / blockNum * blockNum + 1, totalPages));
//
//
//            int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;
//
//
//            model.addAttribute("startPage", startPage);
//            model.addAttribute("endPage", endPage);
//            model.addAttribute("signList", signList);
//
//        }
//            return "sign/signList";
//
//    }

//안되면이거쓰기
//    @GetMapping("/signList")
//    public String signList(@PageableDefault(page = 0, size = 8, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
//                           @RequestParam(name = "subject", required = false) String subject,
//                           @RequestParam(name = "search", required = false) String search,
//                           Model model) {
//
//        Page<SignDto> signList = signService.signSearchPagingList(pageable, subject, search);
//
//        int totalPages = signList.getTotalPages();
//        int newPage = signList.getNumber();
//        long totalElements = signList.getTotalElements();
//        int size = signList.getSize();
//
//        int blockNum = 3;
//        int startPage = Math.max(1, Math.min(newPage / blockNum * blockNum + 1, totalPages));
//
//
//        int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;
//
//
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//        model.addAttribute("signList", signList);
//
//
//
//
//        return "sign/signList";
//    }
//


    @GetMapping("/signList")
    public String signList(@PageableDefault(page = 0, size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                           @RequestParam(name = "subject", required = false) String subject,
                           @RequestParam(name = "search", required = false) String search,
//                           @RequestParam(name = "lastApprover", required = false) String lastApprover,
                           @AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                           Model model) {

//            System.out.println(">>>>>>>>>"+myUserDetails.getName());
//            model.addAttribute("myUserDetails",myUserDetails);
//            String name = myUserDetails.getName();
            String name = "유부장";
            Page<SignDto> signDtoPage = signService.apvList(pageable, subject, search, name);

            int totalPages = signDtoPage.getTotalPages();
            int newPage = signDtoPage.getNumber();
            int blockNum = 10;
            int startPage = (int) (
                    (Math.floor(newPage / blockNum) * blockNum) + 1 <=
                            totalPages ? (Math.floor(newPage / blockNum) * blockNum) + 1 : totalPages);
            int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;

            model.addAttribute("startPage", startPage);
            model.addAttribute("newPage", newPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("signDtoPage", signDtoPage);

        return "sign/signList";


    }


//기존디테일

//    @GetMapping("/detail/{id}")
//    public String detail(@PathVariable("id") Long id, Model model) {
//        SignDto sign = signService.signOne(id);
//        if (sign != null) {
//            model.addAttribute("sign", sign);
//        }
//        return "sign/detail";
//    }


    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        SignDto sign = signService.signOne(id);
        String selectedUserName = sign.getLastApprover(); // 선택된 사용자의 이름 가져오기
        model.addAttribute("sign", sign);
        model.addAttribute("selectedUserName", selectedUserName);
        return "sign/detail";
    }


    //승인자만보이게
//    @GetMapping("/detail/{id}")
//    public String detail(@PathVariable("id") Long id, Model model) {
//        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        // 현재 사용자가 승인자인지를 확인합니다.
//        boolean isApprover = checkUserRole(currentUserName, "APPROVER");
//
//        // 승인자인 경우에만 상세 페이지로 이동합니다.
//        if (isApprover) {
//            SignDto sign = signService.signOne(id);
//            if (sign != null) {
//                model.addAttribute("sign", sign);
//                return "sign/detail";
//            }
//        }

    // 승인자가 아닌 경우에는 접근 거부 페이지를 반환합니다.
    //      return "sign/error";
    //  }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        signService.deleteSign(id);
        return "redirect:/sign/signList";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute SignDto signDto) {
        signService.update(signDto);
        return "redirect:/sign/detail/" + signDto.getId();
    }

    private String getCurrentUserName() {
        // 현재 로그인한 사용자의 이름을 반환하는 로직
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}















