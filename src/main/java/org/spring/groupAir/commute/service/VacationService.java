package org.spring.groupAir.commute.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.commute.dto.VacationDto;
import org.spring.groupAir.commute.entity.CommuteEntity;
import org.spring.groupAir.commute.entity.VacationEntity;
import org.spring.groupAir.commute.repository.CommuteRepository;
import org.spring.groupAir.commute.repository.VacationRepository;
import org.spring.groupAir.commute.service.serviceInterface.VacationServiceInterface;
import org.spring.groupAir.member.entity.MemberEntity;
import org.spring.groupAir.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VacationService implements VacationServiceInterface {

    private final CommuteRepository commuteRepository;
    private final MemberRepository memberRepository;
    private final VacationRepository vacationRepository;

    @Override
    public void vacationCreate(VacationDto vacationDto) {

        LocalDate vacStartDate = vacationDto.getVacStartDate();
        LocalDate vacEndDate = vacationDto.getVacEndDate();

        List<VacationEntity> overlappingVacations = vacationRepository.findOverlappingVacations(
            vacationDto.getEmployeeId(), vacStartDate, vacEndDate);

        if (!overlappingVacations.isEmpty()) {
            throw new IllegalStateException("이미 등록된 휴가와 겹칩니다.");
        }

        LocalDateTime vacStartDateTime = vacationDto.getVacStartDate().atStartOfDay();
        LocalDateTime vacEndDateTime = vacationDto.getVacEndDate().atStartOfDay();

        int vacDays = (int) Duration.between(vacStartDateTime, vacEndDateTime).toDays();

        vacationDto.setMemberEntity(MemberEntity.builder()
            .id(vacationDto.getEmployeeId())
            .build());

        VacationEntity vacationEntity = VacationEntity.builder()
            .vacDays(vacDays + 1)
            .vacType(vacationDto.getVacType())
            .vacEndDate(vacEndDate)
            .vacStartDate(vacStartDate)
            .memberEntity(vacationDto.getMemberEntity())
            .build();

        vacationRepository.save(vacationEntity);
    }

    @Override
    public int vacationPeople() {

        int vPeople = vacationRepository.findAllByDate(LocalDate.now());
        return vPeople;
    }

    @Override
    public int sickVacationPeople() {
        int sickVacationPeople = vacationRepository.findBySickVacationPeople(LocalDate.now());
        return sickVacationPeople;
    }

    @Override
    public void findVacationPerson() {
        List<VacationEntity> vacationEntityList1 = vacationRepository.findVacationPerson(LocalDate.now());
        List<MemberEntity> memberEntityList  = memberRepository.findNotVacationPerson(LocalDate.now());

        for (VacationEntity vacationEntity : vacationEntityList1) {
            if (!vacationEntity.getMemberEntity().getCommuteEntityList().get(vacationEntity.getMemberEntity().getCommuteEntityList().size() - 1).getStatus().equals("휴가")) {
                CommuteEntity commuteEntity = CommuteEntity.builder()
                    .id(vacationEntity.getMemberEntity().getCommuteEntityList().get(vacationEntity.getMemberEntity().getCommuteEntityList().size() - 1).getId())
                    .work(0)
                    .status("휴가")
                    .memberEntity(vacationEntity.getMemberEntity())
                    .build();

                commuteRepository.save(commuteEntity);
            }
        }


        for (MemberEntity memberEntity : memberEntityList) {
            if (memberEntity.getCommuteEntityList().get(memberEntity.getCommuteEntityList().size() - 1).getStatus().equals("휴가")) {
                CommuteEntity commuteEntity = CommuteEntity.builder()
                    .id(memberEntity.getCommuteEntityList().get(memberEntity.getCommuteEntityList().size() - 1).getId())
                    .work(0)
                    .status("퇴근")
                    .memberEntity(memberEntity)
                    .build();

                commuteRepository.save(commuteEntity);
            }
        }

    }
}
