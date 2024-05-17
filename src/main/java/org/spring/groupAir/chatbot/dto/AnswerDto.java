package org.spring.groupAir.chatbot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AnswerDto {

    private Long id;

    private String content;

    private String keyword;

    private EmployeeInfo employeeInfo;

    public AnswerDto employeeInfo(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
        return this;
    }
}
