package org.spring.groupAir.commute.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.commute.dto.CommuteDto;
import org.spring.groupAir.commute.entity.CommuteEntity;
import org.spring.groupAir.commute.repository.CommuteRepository;
import org.spring.groupAir.commute.service.serviceInterface.CommuteServiceInterface;
import org.spring.groupAir.member.dto.MemberDto;
import org.spring.groupAir.member.entity.MemberEntity;
import org.spring.groupAir.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommuteService implements CommuteServiceInterface {

    private final CommuteRepository commuteRepository;
    @Override
    public Page<CommuteDto> commuteList(Pageable pageable, String subject, String search) {

        Page<CommuteEntity> commuteEntityPage;

        if (subject == null || search == null) {
            commuteEntityPage = commuteRepository.findAll(pageable);
        } else if (subject.equals("name")) {
            commuteEntityPage = commuteRepository.findByMemberEntityNameContains(pageable, search);
        } else {
            commuteEntityPage = commuteRepository.findAll(pageable);
        }
        Page<CommuteDto> commuteDtoPage = commuteEntityPage.map(CommuteDto::toCommuteDto);

        return commuteDtoPage;
    }
}
