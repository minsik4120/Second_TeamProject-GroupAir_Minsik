package org.spring.groupAir.department.dto;

import lombok.*;
import org.spring.groupAir.department.entity.TopDepartmentEntity;
import org.spring.groupAir.member.entity.MemberEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentDto {

    private Long id;

    public String departmentName;

    private List<MemberEntity> memberEntityList;

    private TopDepartmentEntity topDepartmentEntity;
}
