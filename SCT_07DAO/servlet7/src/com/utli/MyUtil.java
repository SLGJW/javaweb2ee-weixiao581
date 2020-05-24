package com.utli;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {
    /**
     * 将获取到的字符串类型的日期转化为Date类型
     * @param str
     * @return
     */
    public static Date toDate(String str){
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取数据库连接池对象
     * @return
     */
    public static QueryRunner getDB(){
        DataSource ds = new ComboPooledDataSource();
        QueryRunner qr = new QueryRunner(ds);
        return qr;
    }

}
