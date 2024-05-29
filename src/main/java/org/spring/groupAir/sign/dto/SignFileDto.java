package org.spring.groupAir.sign.dto;

import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SignFileDto {
    private Long id;


    private String signNewFile;


    private String signOldFile;


    private String content;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
