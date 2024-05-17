package org.spring.groupAir.sign.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.spring.groupAir.contraint.BaseTimeEntity;
import org.spring.groupAir.member.entity.MemberEntity;
import org.spring.groupAir.sign.dto.SignDto;

import javax.persistence.*;
import java.util.List;

@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "sign")
public class SignEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sign_id")
    private Long id;

    @Column(nullable = false)
    private String approve;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int signAttachFile;

    @Column(nullable = true)
    private String rejectReason;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "signStatus_id")
    private SignStatusEntity signStatusEntity;

    @OneToMany(mappedBy = "signEntity"
            , fetch = FetchType.LAZY
            , cascade = CascadeType.REMOVE)
    private List<SignFileEntity> signFileEntityList;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private MemberEntity memberEntity;

    public static SignEntity towriteSignEntity(SignDto signDto) {
        SignEntity signEntity = new SignEntity();
        signEntity.setId(signDto.getId());
        signEntity.setApprove(signDto.getApprove());
        signEntity.setTitle(signDto.getTitle());
        signEntity.setContent(signDto.getContent());


        return signEntity;
    }


    public static SignEntity toUpdateEntity(SignDto signDto) {
        SignEntity signEntity=new SignEntity();
        signEntity.setId(signDto.getId());
        signEntity.setApprove(signDto.getApprove());
        signEntity.setTitle(signDto.getTitle());
        signEntity.setContent(signDto.getContent());
        signEntity.setRejectReason(signDto.getRejectReason());
        signEntity.setSignAttachFile(signDto.getSignAttachFile());


        return signEntity;





    }
}






