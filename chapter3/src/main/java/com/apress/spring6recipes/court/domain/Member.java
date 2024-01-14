package com.apress.spring6recipes.court.domain;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@XmlRootElement
public class Member {

    @NotBlank
    public String name;
    @NotBlank
    @Email
    public String email;
    public String phone;

    public Member() {}

    public Member(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
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
}
