package com.snizhel.libraryManagement.payload;

import javax.validation.constraints.NotBlank;

public class ChangPassRequest {
    private Integer id;
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}


