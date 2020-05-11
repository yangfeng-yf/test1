package cn.dk.shiro.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:Create by dk
 * @Date:Create in 20:24:2019/3/11/011
 */
public class MyDate {
    public static String getNowDate(){
        Date date =new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sf.format(date);

        return dateStr;
    }

    /**
     * 主键生成策略
      * @return
     */
    public static int getId(){
        long time = new Date().getTime();
        time = time - 1500000000000l;
        int data = (int) time;
        return data;
    }

}
