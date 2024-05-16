package org.spring.groupAir.department.dto;

import lombok.*;
import org.spring.groupAir.department.entity.DepartmentEntity;
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

    private String departmentName;

    private List<MemberEntity> memberEntityList;

    private TopDepartmentEntity topDepartmentEntity;

    public static DepartmentEntity toWriteDeEntity(DepartmentDto departmentDto) {

//        departmentDto.topDepartmentEntity.TopDepartmentName

        DepartmentEntity departmentEntity = new DepartmentEntity();

        departmentEntity.setId(departmentDto.getId());
        departmentEntity.setDepartmentName(departmentDto.getDepartmentName());
        departmentEntity.setTopDepartmentEntity(departmentDto.getTopDepartmentEntity());
        departmentEntity.setMemberEntityList(departmentDto.getMemberEntityList());

        return departmentEntity;
    }
}
