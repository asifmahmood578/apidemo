package com.apidemo.payload;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegistrationDto {

    private Long id;

    @NotNull(message = "Cannot be null")
    @Size(min=2,max=10, message = "Should be between 2 to 10 characters")
    private String name;

    @Email(message = "invalid Email Address")
    private String emailId;

    @NotNull(message = "Cannot be null")
    @Size(min = 10,max = 10, message = "should be exactly 10 digits")
    private String mobile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
