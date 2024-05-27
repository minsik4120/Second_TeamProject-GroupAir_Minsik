package org.spring.groupAir.schedule.service;

import lombok.RequiredArgsConstructor;
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
          .title(entity.getTitle())
          .content(entity.getContent())
          .startDate(entity.getStartDate())
          .endDate(entity.getEndDate())
          .build();

      scheduleDtoList.add(scheduleDto);
    }

    return scheduleDtoList;
  }

  public void setCalender(ScheduleDto scheduleDto) {
    ScheduleEntity entity = ScheduleEntity
        .builder()
        .title(scheduleDto.getContent())
        .content(scheduleDto.getContent())
        .startDate(scheduleDto.getStartDate())
        .endDate(scheduleDto.getEndDate())
        .build();

   ScheduleEntity scheduleEntity = scheduleRepository.save(entity);

  }
}
