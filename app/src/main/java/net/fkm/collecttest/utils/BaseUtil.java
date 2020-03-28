package net.fkm.collecttest.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 基础工具类
 */
public class BaseUtil {

    /**
     * 获取Assets目录的json文件
     *
     * @return json data
     */
    public static String getAssetsJson(Context context, String fileName) {
        InputStream input = null;
        try {
            input = context.getAssets().open(fileName);
            String json = convertStreamToString(input);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * input 流转换为字符串
     *
     * @param is
     * @return
     */
    private static String convertStreamToString(InputStream is) {
        String s = null;
        try {
            Scanner scanner = new Scanner(is, "UTF-8").useDelimiter("\\A");
            if (scanner.hasNext()) {
                s = scanner.next();
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String getNowDateTimeFormat() {
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat s_format = new SimpleDateFormat(format);
        Date d_date = new Date();
        String s_date = "";
        s_date = s_format.format(d_date);
        return s_date;
    }

}
