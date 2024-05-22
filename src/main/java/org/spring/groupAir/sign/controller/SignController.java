package org.spring.groupAir.sign.controller;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.config.MyUserDetailsImpl;
import org.spring.groupAir.member.dto.MemberDto;
import org.spring.groupAir.member.service.MemberService;
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
    private final MemberService memberService;
    private final MemberServiceInterface memberServiceInterface;

    @GetMapping({"", "/index"})
    public String index(@AuthenticationPrincipal MyUserDetailsImpl myUserDetails, Model model) {

        String Position = memberService.findPosition(myUserDetails.getName());

        model.addAttribute( "myUserDetails", myUserDetails);

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

        List<MemberDto> memberDtoList = memberService.findBujang();

        model.addAttribute("members", memberDtoList);


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

        String name = myUserDetails.getName();
//        String name = "유부장";
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

//    @PostMapping("/update")
//    public String update(@ModelAttribute SignDto signDto) {
//        signService.update(signDto);
//        return "redirect:/sign/signOk/" + signDto.getId();
//    }

    private String getCurrentUserName() {
        // 현재 로그인한 사용자의 이름을 반환하는 로직
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/signOk")
    public String signOk() {

        return "sign/signOk";

    }


    @PostMapping("/signOk")
    public String signOk(@Valid SignDto signDto, BindingResult bindingResult, Model model) throws IOException {
        if (bindingResult.hasErrors()) {


            return "sign/signList";
        }

//        System.out.println(signDto.getRejectReason()+" <<< 승인");

        signService.update(signDto);

        List<SignDto> signOkList = signService.signSubContnetList(signDto.getSubContent());

        model.addAttribute("signOkList", signOkList);

        System.out.println(signOkList + " signOkList");

        // 페이지 이동
//        return "redirect:/sign/signOk";
        return "sign/signOk";

    }

    @PostMapping("/write")
    public String writeOk(@Valid SignDto signDto, BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return "sign/write";
        }
//        signService.write(signDto);

        Long id = signService.insertSign(signDto);


        return "redirect:/sign/signList/";

    }


}















