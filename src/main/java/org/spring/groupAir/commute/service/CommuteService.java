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
import java.time.LocalDateTime;

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

    @Override
    public void workIn(Long id) {
        CommuteEntity commuteEntity1 = commuteRepository.findById(id).get();
        if(commuteEntity1.getInTime() == null && commuteEntity1.getOutTime() == null){
            CommuteEntity commuteEntity2 = CommuteEntity
                .builder()
                .id(id)
                .work(1)
                .inTime(LocalDateTime.now())
                .outTime(commuteEntity1.getInTime())
                .memberEntity(commuteEntity1.getMemberEntity()).build();
            commuteRepository.save(commuteEntity2);
        }
        else{
            CommuteEntity commuteEntity2 = CommuteEntity
                .builder()
                .work(1)
                .inTime(LocalDateTime.now())
                .outTime(commuteEntity1.getInTime())
                .memberEntity(commuteEntity1.getMemberEntity()).build();
            commuteRepository.save(commuteEntity2);
        }
    }
    @Override
    public void workOut(Long id) {
        CommuteEntity commuteEntity1 = commuteRepository.findById(id).get();

        if(commuteEntity1.getInTime() == null && commuteEntity1.getOutTime() == null){
            CommuteEntity commuteEntity2 = CommuteEntity
                .builder()
                .id(id)
                .work(0)
                .inTime(LocalDateTime.now())
                .outTime(commuteEntity1.getInTime())
                .memberEntity(commuteEntity1.getMemberEntity()).build();
            commuteRepository.save(commuteEntity2);
        }
        else{
            CommuteEntity commuteEntity2 = CommuteEntity
                .builder()
                .work(0)
                .inTime(LocalDateTime.now())
                .outTime(commuteEntity1.getInTime())
                .memberEntity(commuteEntity1.getMemberEntity()).build();
            commuteRepository.save(commuteEntity2);
        }
    }

}
