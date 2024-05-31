package org.spring.groupAir.schedule.service.scheduleInterface;

import org.spring.groupAir.schedule.dto.ScheduleDto;

import java.util.List;

public interface ScheduleInterface {


  List<ScheduleDto> scheduleListAll();

  void setCalendar(ScheduleDto scheduleDto);

  //----------------------------------------------------------//
  List<ScheduleDto> mySchedule(Long id);


  List<ScheduleDto> getScheduleByEmployeeId(Long id);

    List<ScheduleDto> todayAllSchedule();

  List<ScheduleDto> todayMySchedule(Long id);

}
