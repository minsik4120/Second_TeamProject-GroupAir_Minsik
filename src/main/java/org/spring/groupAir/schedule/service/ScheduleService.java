package org.spring.groupAir.schedule.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.config.MyUserDetailsImpl;
import org.spring.groupAir.member.dto.MemberDto;
import org.spring.groupAir.member.entity.MemberEntity;
import org.spring.groupAir.member.repository.MemberRepository;
import org.spring.groupAir.schedule.dto.ScheduleDto;
import org.spring.groupAir.schedule.entity.ScheduleEntity;
import org.spring.groupAir.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
  private final ScheduleRepository scheduleRepository;
  private final MemberRepository memberRepository;
  public List<ScheduleDto> scheduleListAll() {

    List<ScheduleDto> scheduleDtoList = new ArrayList<>();
    List<ScheduleEntity> scheduleEntities = scheduleRepository.findAll();

    for (ScheduleEntity entity : scheduleEntities) {
      ScheduleDto scheduleDto = ScheduleDto.builder()
          .id(entity.getId())
          .content(entity.getContent())
          .start(entity.getStart())
          .end(entity.getEnd())
          .memberEntity(entity.getMemberEntity())
          .build();

      scheduleDtoList.add(scheduleDto);
    }

    return scheduleDtoList;
  }

  public void setCalendar(ScheduleDto scheduleDto) {

    scheduleDto.setMemberEntity(MemberEntity.builder().id(scheduleDto.getEmployeeId()).build());

    ScheduleEntity entity = ScheduleEntity
        .builder()
//        .title(scheduleDto.getContent())
        .content(scheduleDto.getContent())
        .start(scheduleDto.getStart())
        .end(scheduleDto.getEnd())
        .memberEntity(scheduleDto.getMemberEntity())
        .build();

   ScheduleEntity scheduleEntity = scheduleRepository.save(entity);


  }


}
