package com.example.myshixun.release;

import java.util.List;

public class Eventstring {
    String showimage;
    int Code;

    public Eventstring(String showimage, int code) {
        this.showimage = showimage;
        Code = code;
    }

    public String getShowimage() {
        return showimage;
    }

    public void setShowimage(String showimage) {
        this.showimage = showimage;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }
}
