package org.spring.groupAir.schedule.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.member.entity.MemberEntity;
import org.spring.groupAir.schedule.dto.ScheduleDto;
import org.spring.groupAir.schedule.entity.ScheduleEntity;
import org.spring.groupAir.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
  private final ScheduleRepository scheduleRepository;
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

    System.out.println(scheduleDto+"  <<< scheduleDto ");
    System.out.println(scheduleDto.getEmployeeId()+"  <<<   scheduleDto.getEmployeeId() ");

    scheduleDto.setMemberEntity(MemberEntity.builder().id(scheduleDto.getEmployeeId()).build());

    ScheduleEntity entity = ScheduleEntity
        .builder()
//        .title(scheduleDto.getContent())
        .content(scheduleDto.getContent())
        .start(scheduleDto.getStart())
        .end(scheduleDto.getEnd())
        .memberEntity(scheduleDto.getMemberEntity())
        .build();


    System.out.println(entity.getEnd()+"  <<< getEnd ");
    System.out.println(entity.getStart()+"  <<< scheduleDto ");

    ScheduleEntity scheduleEntity = scheduleRepository.save(entity);

  }
}