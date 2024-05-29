package org.spring.groupAir.chatbot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AnswerDto {

    private Long id;

    private String content;

    private String keyword;

    private EmployeeInfo employee;

    private List<EmployeeInfo> employeeInfo;

    public AnswerDto employeeInfo(EmployeeInfo employee) {
        this.employee = employee;
        return this;
    }
}
