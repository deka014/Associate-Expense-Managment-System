package com.adp.IdentityService.IdentityService.Dto;

import com.adp.EntitiesService.enums.Department;
import com.adp.EntitiesService.enums.ExpenseStatus;
import com.adp.EntitiesService.enums.JobTitle;
import com.adp.EntitiesService.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private JobTitle jobTitle;
    private Department department;
    private String token;
    private Role role;
    private Integer managerId;


}