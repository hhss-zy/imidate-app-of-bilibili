package com.example.myshixun.util;


/**
 * <p>
 * 
 * </p>
 *
 * @author czy
 * @since 2021-12-11
 */
public class Login  {

    private int id;
    private int phone;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Login(int phone, String password) {
        this.phone = phone;
        this.password = password;
    }

}
