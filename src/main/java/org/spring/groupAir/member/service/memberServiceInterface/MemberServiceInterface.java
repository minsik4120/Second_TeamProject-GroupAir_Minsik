package org.spring.groupAir.member.service.memberServiceInterface;

import org.spring.groupAir.member.dto.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface MemberServiceInterface {

    Page<MemberDto> memberList(Pageable pageable, String subject, String search);

    List<MemberDto> memberList();

    MemberDto memberDetail(Long id);


    void memberUpdate(MemberDto memberDto) throws IOException;

    void memberDelete(Long id);

    Long memberJoin(MemberDto memberDto) throws IOException;
    String findName(Long id);

    List<MemberDto> selectPilot();

    Page<MemberDto> pageSelectPilot(Pageable pageable);

    List<MemberDto> fireList(MemberDto memberDto);


    String findUserEmailByNameAndPhone(String name, String phone);

    String findUserPwByUserEmailAndName(String userEmail, String name);

    boolean changePasswordByEmailAndName(String email, String name, String newPassword);


    MemberDto findMyId(String userEmail);
}
