package org.spring.groupAir.airplane.dto;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AirplaneDto {

    private Long id;

    private String fromTime;

    private String fromArea;

    private String ToTime;

    private String ToArea;

    private String airplane;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
