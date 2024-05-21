package org.spring.groupAir.member.service.memberServiceInterface;

import org.spring.groupAir.member.dto.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface MemberServiceInterface {

    Page<MemberDto> memberList(Pageable pageable, String subject, String search);

    Long memberJoin(MemberDto memberDto) throws IOException;
//sign추가한거
    Page<MemberDto> findMembersByNameContaining(String name, Pageable pageable);
}
