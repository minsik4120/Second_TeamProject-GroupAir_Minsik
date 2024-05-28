package org.spring.groupAir.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.config.MyUserDetailsImpl;
import org.spring.groupAir.schedule.dto.ScheduleDto;
import org.spring.groupAir.schedule.service.ScheduleService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
@RequiredArgsConstructor
public class ApiController {

  private final ScheduleService scheduleService;


  @GetMapping("/events")
  public List<ScheduleDto> eventsCalender() {
    List<ScheduleDto> scheduleDtoList = scheduleService.scheduleListAll();

    return scheduleDtoList;
  }


  // 일정 저장
  @PostMapping("/calendar")
  public List<ScheduleDto> setCalendar(@ModelAttribute ScheduleDto scheduleDto,
                                       @AuthenticationPrincipal MyUserDetailsImpl myUserDetails){

    scheduleDto.setEmployeeId(myUserDetails.getMemberEntity().getId());
    scheduleService.setCalendar(scheduleDto);

    return scheduleService.scheduleListAll();

  }

  @GetMapping("/calendar")
  public List<ScheduleDto> getCalendar() {
    return scheduleService.scheduleListAll();
  }





}
