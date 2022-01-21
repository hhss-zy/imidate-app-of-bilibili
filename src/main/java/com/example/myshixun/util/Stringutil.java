package com.example.myshixun.util;

import androidx.annotation.Nullable;

public class Stringutil {
    public static boolean isempty(String src) {
        if (src == null || src.length() <= 0) {
            return true;
        } else {
            return false;
        }
    }
}
