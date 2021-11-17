package com.minitao.user.utils;

/**
 * @Description:
 * @Author: July
 * @Date: 2021-11-15 19:47
 **/
public class CodeGen {
    public static String generate(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int num = (int)(Math.random()*1000%10);
            sb.append(String.valueOf(num));
        }
        return sb.toString();
    }
}
