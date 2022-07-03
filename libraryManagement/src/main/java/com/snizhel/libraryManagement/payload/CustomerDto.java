package com.snizhel.libraryManagement.payload;

import java.io.Serializable;
import java.util.Objects;

public class CustomerDto implements Serializable {
    private final Integer id;
    private final String name;
    private final String password;
    private final String phone;
    private final String address;
    private final String birthday;

    public CustomerDto(Integer id, String name, String password, String phone, String address, String birthday) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto entity = (CustomerDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.password, entity.password) &&
                Objects.equals(this.phone, entity.phone) &&
                Objects.equals(this.address, entity.address) &&
                Objects.equals(this.birthday, entity.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, phone, address, birthday);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "password = " + password + ", " +
                "phone = " + phone + ", " +
                "address = " + address + ", " +
                "birthday = " + birthday + ")";
    }
}
