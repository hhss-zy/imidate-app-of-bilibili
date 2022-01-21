package com.example.myshixun.release;

import java.util.List;

public class EventList<T> {
    List<T> listdata;

    public EventList(List<T> listdata) {
        this.listdata = listdata;
    }

    public List<T> getListdata() {
        return listdata;
    }

    public void setListdata(List<T> listdata) {
        this.listdata = listdata;
    }
}
