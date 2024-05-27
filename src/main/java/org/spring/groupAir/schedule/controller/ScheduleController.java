package org.spring.groupAir.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/my",produces = "application/json")
public class ScheduleController {

  @GetMapping("/schedule")
  public String schedule(){
    return "calender/myCalender";
  }
}
