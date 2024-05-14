package org.spring.groupAir.member.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.member.repository.MemberRepository;
import org.spring.groupAir.member.service.memberServiceInterface.MemberServiceInterface;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberServiceInterface {

    private final MemberRepository memberRepository;
}
