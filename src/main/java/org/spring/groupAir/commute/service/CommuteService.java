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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommuteService implements CommuteServiceInterface {

    private final CommuteRepository commuteRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long workIn(Long id) {
        CommuteEntity commuteEntity1 = commuteRepository.findById(id).get();
        Long memberId = commuteEntity1.getMemberEntity().getId();
        if (commuteEntity1.getInTime() == null) {
            CommuteEntity commuteEntity2 = CommuteEntity
                .builder()
                .id(id)
                .work(1)
                .status("출근")
                .inTime(LocalDateTime.now())
//                .outTime(commuteEntity1.getOutTime())
                .memberEntity(commuteEntity1.getMemberEntity()).build();
            commuteRepository.save(commuteEntity2);
        } else {
            CommuteEntity commuteEntity2 = CommuteEntity
                .builder()
                .work(1)
                .status("출근")
                .inTime(LocalDateTime.now())
//                .outTime(commuteEntity1.getOutTime())
                .memberEntity(commuteEntity1.getMemberEntity()).build();
            commuteRepository.save(commuteEntity2);
        }

        return memberId;
    }

    @Override
    public Long workOut(Long id) {
        CommuteEntity commuteEntity1 = commuteRepository.findById(id).get();
        Long memberId = commuteEntity1.getMemberEntity().getId();

        if (commuteEntity1.getInTime() != null && commuteEntity1.getOutTime() == null) {
            LocalDateTime outTime = LocalDateTime.now();
            Duration totalWork = Duration.between(commuteEntity1.getInTime(), outTime);

            CommuteEntity commuteEntity2 = CommuteEntity
                .builder()
                .id(id)
                .work(0)
                .status("퇴근")
                .inTime(commuteEntity1.getInTime())
                .outTime(outTime)
                .totalWork(totalWork)
                .memberEntity(commuteEntity1.getMemberEntity()).build();
            commuteRepository.save(commuteEntity2);
        } else {
            CommuteEntity commuteEntity2 = CommuteEntity
                .builder()
                .work(0)
                .status("퇴근")
                .inTime(commuteEntity1.getInTime())
                .outTime(LocalDateTime.now())
                .totalWork(Duration.between(commuteEntity1.getOutTime(), commuteEntity1.getInTime()))
                .memberEntity(commuteEntity1.getMemberEntity()).build();
            commuteRepository.save(commuteEntity2);
        }

        return memberId;
    }

    @Override
    public List<CommuteDto> commuteList(Long id) {
        List<CommuteEntity> commuteEntityList = commuteRepository.findByMemberEntityId(id);

        List<CommuteDto> commuteDtoList =
            commuteEntityList.stream().map(CommuteDto::toCommuteDto).collect(Collectors.toList());

        return commuteDtoList;
    }

    @Override
    public void createCommute(Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        CommuteEntity commuteEntity = CommuteEntity
            .builder()
            .status("퇴근")
            .memberEntity(memberEntity)
            .build();
        commuteRepository.save(commuteEntity);
    }

}
