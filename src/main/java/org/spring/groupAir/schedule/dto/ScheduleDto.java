package org.spring.groupAir.schedule.dto;

import lombok.*;
import org.spring.groupAir.member.entity.MemberEntity;
import org.spring.groupAir.schedule.entity.ScheduleEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {

  private Long id;
//
//  private String title;

  private String content;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private String start;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private String end;

  private Long employeeId;

  private MemberEntity memberEntity;


  public static ScheduleDto toScheduleDto(ScheduleEntity scheduleEntity) {
    ScheduleDto scheduleDto=new ScheduleDto();
    scheduleDto.setId(scheduleEntity.getId());
    scheduleDto.setStart(scheduleEntity.getStart());
    scheduleDto.setEnd(scheduleEntity.getEnd());
    scheduleDto.setContent(scheduleEntity.getContent());
    scheduleDto.setMemberEntity(scheduleEntity.getMemberEntity());
    scheduleDto.setEmployeeId(scheduleEntity.getMemberEntity().getId());
    return scheduleDto;
  }
}
