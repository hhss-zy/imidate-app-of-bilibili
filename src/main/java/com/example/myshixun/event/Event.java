package com.example.myshixun.event;

public class Event {
    String mark;
    User user;

    public Event(String mark, User user) {
        this.mark = mark;
        this.user = user;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
