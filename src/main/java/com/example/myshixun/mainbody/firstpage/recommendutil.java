package com.example.myshixun.mainbody.firstpage;

public class recommendutil {
    int headimage;
    String name;
    String time;
    int imagevoid;
    String voidetime;
    String title;

    public recommendutil(int headimage, String name, String time, int imagevoid, String voidetime,String title) {
        this.headimage = headimage;
        this.name = name;
        this.time = time;
        this.imagevoid = imagevoid;
        this.voidetime=voidetime;
        this.title = title;
    }

    public String getVoidetime() {
        return voidetime;
    }

    public void setVoidetime(String voidetime) {
        this.voidetime = voidetime;
    }

    public int getHeadimage() {
        return headimage;
    }

    public void setHeadimage(int headimage) {
        this.headimage = headimage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImagevoid() {
        return imagevoid;
    }

    public void setImagevoid(int imagevoid) {
        this.imagevoid = imagevoid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
