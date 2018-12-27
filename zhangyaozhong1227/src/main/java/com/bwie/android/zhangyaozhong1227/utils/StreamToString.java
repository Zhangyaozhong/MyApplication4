package com.bwie.android.zhangyaozhong1227.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamToString {
    public static String streamChange(InputStream inputStream,String format){
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer buffer = new StringBuffer();
        String a = "";
        try {
            while ((a = reader.readLine())!=null){
                buffer.append(a);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
