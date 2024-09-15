package com.adp.EntitiesService.entities;


import java.util.List;


import com.adp.EntitiesService.enums.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name = "User_Group3_Oct3_2")
public class User {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Phone", nullable = false)
    private String phone;

    @Column(columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private JobTitle jobTitle;

    @Column(columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private Department department;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "managerId", nullable = false)
    private Integer managerId;


    @JsonManagedReference
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Expense> expenses;

    public Integer getId() {
        return id;
    }

    public User() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public User(Integer id, String name, String email, String phone, JobTitle jobTitle, Department department, String password, Role role, Integer managerId, List<Expense> expenses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.jobTitle = jobTitle;
        this.department = department;
        this.password = password;
        this.role = role;
        this.managerId = managerId;
        this.expenses = expenses;

    }

}
