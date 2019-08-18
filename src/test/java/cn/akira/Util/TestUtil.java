package cn.akira.Util;

import org.junit.Test;

import java.text.SimpleDateFormat;

public class TestUtil {

    @Test
    public void 时间戳转日期(){

        double timeStamp = -30609820800000d;
        String result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timeStamp);
        System.out.println("13位数的时间戳（毫秒）--->Date:" + result);
    }
}
