package com.example.myshixun.util;

import java.util.List;

public class basemodle1<T> {
    int code;
    String mes;
    T data;
    private String time;
    private String title;
    private String message;
    private String name;

    List<T> data_list;

    public basemodle1(int code, String message) {
        this.code = code;
        this.mes = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public basemodle1( String time, String title, String name) {
        this.time = time;
        this.title = title;
        this.name = name;
    }

    public basemodle1(String title, String name) {
        this.title = title;
        this.name = name;
    }

    public basemodle1(int code, String message, T data, List<T> data_list) {
        this.code = code;
        this.mes = message;
        this.data = data;
        this.data_list = data_list;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<T> getData_list() {
        return data_list;
    }

    public void setData_list(List<T> data_list) {
        this.data_list = data_list;
    }
}
