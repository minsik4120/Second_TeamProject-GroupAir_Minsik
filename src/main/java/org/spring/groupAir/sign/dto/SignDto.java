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


    public String approve;


    public String title;


    public String content;


    public int signAttachFile;


    public String rejectReason;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
