package org.spring.groupAir.member.service.memberServiceInterface;

import org.spring.groupAir.member.dto.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.util.List;

public interface MemberServiceInterface {

    Page<MemberDto> memberList(Pageable pageable, String subject, String search);

    void memberJoin(MemberDto memberDto) throws IOException;

    MemberDto memberDetail(Long id);


    void memberUpdate(MemberDto memberDto) throws IOException;


    void memberDelete(Long id);


}
