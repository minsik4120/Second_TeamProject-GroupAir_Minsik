package org.spring.groupAir.chatbot.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmployeeInfo {

    private String phone;
    private String name;
    private String role;
    private String userEmail;
    private String deptName;
    private String email;

}
