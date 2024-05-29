package org.spring.groupAir.schedule.service.scheduleInterface;

import org.spring.groupAir.schedule.dto.ScheduleDto;

import java.util.List;

public interface ScheduleInterface {


  //----------------------------------------------------------//
  List<ScheduleDto> mySchedule(Long id);


  List<ScheduleDto> getScheduleByEmployeeId(Long id);

}
