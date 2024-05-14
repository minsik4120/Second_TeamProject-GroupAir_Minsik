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

    public String fromTime;

    public String fromArea;

    public String ToTime;

    public String ToArea;

    public String airplane;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
