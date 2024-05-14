package org.spring.groupAir.member.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.member.dto.MemberDto;
import org.spring.groupAir.member.entity.MemberEntity;
import org.spring.groupAir.member.repository.MemberRepository;
import org.spring.groupAir.member.service.memberServiceInterface.MemberServiceInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberServiceInterface {

    private final MemberRepository memberRepository;
    @Override
    public Page<MemberDto> memberList(Pageable pageable, String subject, String search) {
        Page<MemberEntity> memberEntityPage;

        if (subject == null || search == null) {
            memberEntityPage = memberRepository.findAll(pageable);
        } else if (subject.equals("name")) {
            memberEntityPage = memberRepository.findByNameContains(pageable, search);
        } else {
            memberEntityPage = memberRepository.findAll(pageable);
        }
        Page<MemberDto> memberDtoPage = memberEntityPage.map(MemberDto::toMemberDto);

        return memberDtoPage;
    }
}
