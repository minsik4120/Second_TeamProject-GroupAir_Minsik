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


    public String signNewFile;


    public String signOldFile;


    public String content;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
