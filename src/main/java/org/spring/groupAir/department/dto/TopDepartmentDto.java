package org.spring.groupAir.department.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.groupAir.contraint.BaseTimeEntity;
import org.spring.groupAir.department.entity.DepartmentEntity;
import org.spring.groupAir.department.entity.TopDepartmentEntity;
import org.spring.groupAir.member.entity.MemberEntity;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopDepartmentDto{

    private Long id;

    private String topDepartmentName;

    private List<DepartmentEntity> departmentEntityList;

}
