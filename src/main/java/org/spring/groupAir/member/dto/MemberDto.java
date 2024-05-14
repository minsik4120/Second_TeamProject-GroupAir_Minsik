package org.spring.groupAir.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.groupAir.board.entity.BoardEntity;
import org.spring.groupAir.commute.entity.CommuteEntity;
import org.spring.groupAir.department.entity.DepartmentEntity;
import org.spring.groupAir.member.entity.MemberFileEntity;
import org.spring.groupAir.member.entity.PositionEntity;
import org.spring.groupAir.role.Role;
import org.spring.groupAir.salery.entity.SalaryEntity;
import org.spring.groupAir.schedule.entity.ScheduleEntity;
import org.spring.groupAir.sign.entity.SignEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MemberDto {

    private Long id;

    private Role role;

    public String name;

    @Size(min = 3,max = 255)
    @NotBlank(message = "이메일을 입력해주세요")
    public String userEmail;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String userPw;

    private String address;

    private String phone;

    private String employeeDate;

    private String resignationDate;

    private int memberAttachFile;

    private List<MemberFileEntity> memberFileEntityList;

    private List<BoardEntity> boardEntityList;

    private List<CommuteEntity> commuteEntityList;

    private List<SalaryEntity> salaryEntityList;

    private List<ScheduleEntity> scheduleEntityList;

    private DepartmentEntity departmentEntity;

    private PositionEntity positionEntity;

    private List<SignEntity> signEntityList;
}
