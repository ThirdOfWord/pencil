package com.freeter.utils;

/**
 * @Auther: hao
 * @Date: 2019/7/17 16:55
 * @Description:
 */
public class ThreadTraceUtils {
    private static ThreadLocal<String> threadLocal=new ThreadLocal<>();
    public static String getTrace(){return threadLocal.get();}
    public static void setTrace(String trace){
        threadLocal.set(trace);
    }
}
