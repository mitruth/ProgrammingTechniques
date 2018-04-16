package com.dan.SD_2.service.dto;

import javax.persistence.Entity;
import java.io.Serializable;

public class StudentDTO implements Serializable {
    private String name;
    private String email;
    private String password;
    private String hobby;
    private int groupNumber;

    public StudentDTO() {}

    public StudentDTO(String name, String email, String password, String hobby, int groupNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.hobby = hobby;
        this.groupNumber = groupNumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", hobby='" + hobby + '\'' +
                ", groupNumber=" + groupNumber +
                '}';
    }
}

