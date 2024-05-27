package org.spring.groupAir.airplane.dto;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.groupAir.member.entity.MemberEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AirplaneDto {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fromTime;

    private String fromArea;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime toTime;

    private String toArea;

    private int timeTaken;

    private String airplane;

    private String status;

    private MemberEntity memberEntity;

    private Long memberId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
