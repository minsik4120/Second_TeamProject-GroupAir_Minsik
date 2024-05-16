package org.spring.groupAir.sign.dto;

import lombok.*;
import org.spring.groupAir.sign.entity.SignEntity;
import org.springframework.web.multipart.MultipartFile;

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

    private String signNewFile;

    private String signOldFile;



    public static SignDto toSelectSignDto(SignEntity signEntity) {

        SignDto signDto=new SignDto();
        signDto.setId(signEntity.getId());
        signDto.setApprove(signEntity.getApprove());
        signDto.setTitle(signEntity.getTitle());
        signDto.setContent(signEntity.getContent());
        signDto.setSignAttachFile(signEntity.getSignAttachFile());
        signDto.setRejectReason(signEntity.getRejectReason());
        signDto.setCreateTime(signEntity.getCreateTime());
        signDto.setUpdateTime(signEntity.getUpdateTime());


        return signDto;

    }

//    public static SignDto toSignDto(SignEntity signEntity) {
//
//        SignDto signDto =new SignDto();
//        signDto.setId(signEntity.getId());
//        signDto.setApprove(signEntity.getApprove());
//        signDto.setTitle(signEntity.getTitle());
//        signDto.setContent(signEntity.getContent());
//        signDto.setRejectReason(signEntity.getRejectReason());
//        signDto.setCreateTime(signEntity.getCreateTime());
//        signDto.setUpdateTime(signEntity.getUpdateTime());
//
//        if(signEntity.getSignAttachFile() == 0){
//            signDto.setSignAttachFile(signEntity.getSignAttachFile());
//        }else {
//            signDto.setSignAttachFile(signDto.getSignAttachFile());
//            signDto.setSignNewFile(signEntity.getSignFileEntityList().get(0).getSignNewFile());
//            signDto.setSignOldFile(signEntity.getSignFileEntityList().get(0).getSignOldFile());
//        }
//return signDto;




    }


