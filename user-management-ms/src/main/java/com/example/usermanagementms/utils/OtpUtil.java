package com.example.usermanagementms.utils;

import java.util.Random;

public class OtpUtil {
    public static String generate(){
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }
}
