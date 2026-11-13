package com.example.DTOExample.DTOS;

import jakarta.validation.constraints.*;

public class CreateStudentRequestDTO {
    @NotBlank(message = "Name can not be Null ,Empty,Blank !")
    @Size(min = 5,max = 50,message = "Name length minimum 5 character and Maximum 50 !")
    private String name;

    @Min(value = 18,message = "Must be atleast 18 year old !")
    @NotNull(message = "Age is Required ! ")
    private Integer age;

    @Email(message = "Email must a valid email address")
    @NotBlank(message = "Email is required !")
    private String email;

    @NotNull(message = "Roll no is required !")
    private Integer rollNo;

    @NotBlank(message ="Subject is required ! " )
    private String subject;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
