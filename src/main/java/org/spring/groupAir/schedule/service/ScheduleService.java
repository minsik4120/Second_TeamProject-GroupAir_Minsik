package org.spring.groupAir.schedule.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.member.entity.MemberEntity;
import org.spring.groupAir.schedule.dto.ScheduleDto;
import org.spring.groupAir.schedule.entity.ScheduleEntity;
import org.spring.groupAir.schedule.repository.ScheduleRepository;
import org.spring.groupAir.schedule.service.scheduleInterface.ScheduleInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService implements ScheduleInterface {
  private final ScheduleRepository scheduleRepository;

  @Override
  public List<ScheduleDto> scheduleListAll() {

    List<ScheduleDto> scheduleDtoList = new ArrayList<>();
    List<ScheduleEntity> scheduleEntities = scheduleRepository.findAll();

    for (ScheduleEntity entity : scheduleEntities) {
      ScheduleDto scheduleDto = ScheduleDto.builder()
          .id(entity.getId())
          .employeeId(entity.getMemberEntity().getId())
          .content(entity.getContent())
          .start(entity.getStart())
          .end(entity.getEnd())
          .memberEntity(entity.getMemberEntity())
          .build();

      scheduleDtoList.add(scheduleDto);
    }

    return scheduleDtoList;
  }

  @Override
  public void setCalendar(ScheduleDto scheduleDto) {


    System.out.println(scheduleDto + "  <<< scheduleDto ");
    System.out.println(scheduleDto.getEmployeeId() + "  <<<   scheduleDto.getEmployeeId() ");


    scheduleDto.setMemberEntity(MemberEntity.builder().id(scheduleDto.getEmployeeId()).build());

    ScheduleEntity entity = ScheduleEntity
        .builder()
//        .title(scheduleDto.getContent())

        .content(scheduleDto.getContent())
        .start(scheduleDto.getStart())
        .end(scheduleDto.getEnd())
        .memberEntity(scheduleDto.getMemberEntity())
        .build();


    System.out.println(entity.getEnd() + "  <<< getEnd ");
    System.out.println(entity.getStart() + "  <<< scheduleDto ");

    ScheduleEntity scheduleEntity = scheduleRepository.save(entity);

  }

  //----------------------------------------------------------//
  @Override
  public List<ScheduleDto> mySchedule(Long id) {


    List<ScheduleEntity> scheduleEntityList = scheduleRepository.findByMemberEntityId(id);


    List<ScheduleDto> scheduleDtoList = scheduleEntityList.stream().map(scheduleEntity ->
        ScheduleDto.builder()
            .id(scheduleEntity.getId())
            .memberEntity(scheduleEntity.getMemberEntity())
            .content(scheduleEntity.getContent())
            .employeeId(scheduleEntity.getMemberEntity().getId())
            .start(scheduleEntity.getStart())
            .end(scheduleEntity.getEnd())
            .build()).collect(Collectors.toList());


    return scheduleDtoList;
  }

  @Override
  public List<ScheduleDto> getScheduleByEmployeeId(Long id) {
    List<ScheduleEntity> scheduleEntityList = scheduleRepository.findByMemberEntityId(id);

    List<ScheduleDto> scheduleDtoList = scheduleEntityList.stream()
        .map(scheduleEntity -> ScheduleDto.builder()
            .id(scheduleEntity.getId())
            .memberEntity(scheduleEntity.getMemberEntity())
            .employeeId(scheduleEntity.getMemberEntity().getId())
            .content(scheduleEntity.getContent())
            .start(scheduleEntity.getStart())
            .end(scheduleEntity.getEnd())
            .build())
        .collect(Collectors.toList());
//    1
    return scheduleDtoList;
  }


}