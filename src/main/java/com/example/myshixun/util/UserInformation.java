package com.example.myshixun.util;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 
 * </p>
 *
 * @author czy
 * @since 2021-12-14
 */

public class UserInformation {


    private Integer phone;

    private String username;

    private String title;

    private String userimage;

    private String contentimage;

    private String uoloadtime;

    private String sex;

    private String brithday;

    public UserInformation(Integer phone, String username, String sex, String brithday) {
        this.phone = phone;
        this.username = username;
        this.sex = sex;
        this.brithday = brithday;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBrithday() {
        return brithday;
    }

    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }
    public String getContentimage() {
        return contentimage;
    }

    public void setContentimage(String contentimage) {
        this.contentimage = contentimage;
    }

    public String getUoloadtime() {
        return uoloadtime;
    }

    public void setUoloadtime(String uoloadtime) {
        this.uoloadtime = uoloadtime;
    }
}
