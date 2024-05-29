package org.spring.groupAir.sign.dto;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SignDto {
    private Long id;


    private String approve;


    private String title;


    private String content;


    private int signAttachFile;


    private String rejectReason;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
