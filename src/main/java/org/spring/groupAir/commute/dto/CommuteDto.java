package org.spring.groupAir.commute.dto;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.groupAir.member.entity.MemberEntity;

import javax.persistence.Column;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CommuteDto {

    private Long id;

    public int vacation;

    public String inTime;

    private String outTime;

    private String cause;

    private MemberEntity memberEntity;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
