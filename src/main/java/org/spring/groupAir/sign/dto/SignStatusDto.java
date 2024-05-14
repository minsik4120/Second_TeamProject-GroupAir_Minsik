package org.spring.groupAir.sign.dto;

import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SignStatusDto {
    private Long id;

    public String signOkTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
