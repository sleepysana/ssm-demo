package cn.akira.util;

import cn.akira.returnable.CommonData;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    @Test
    public void 时间戳转日期() {

        double timeStamp = -30609820800000d;
        String result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timeStamp);
        System.out.println("13位数的时间戳（毫秒）--->Date:" + result);
    }

    @Test
    public void thisIsAMethod() {
        System.out.println("哈哈哈哈".indexOf(" "));
    }

    @Test
    public void cnm() {
        try {
            CommonData file = getFile("rsa.keystroe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("SameParameterValue")
    private CommonData getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        /*
         getResource()方法会去classpath下找这个文件，获取到url resource, 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
         */
        URL url = classLoader.getResource(fileName);
        /*
         * url.getFile() 得到这个文件的绝对路径
         */
        CommonData data = new CommonData();
        if (url != null) {
            System.out.println(url.getFile().substring(1));
            File file = new File(url.getFile());
            if (file.exists()) {
                data.setResource(file);
                return data;
            } else {
                data.setFlag(false);
                data.setMessage("加载秘钥文件失败");
                System.err.println(data.getMessage());
                return data;
            }
        } else {
            data.setFlag(false);
            data.setMessage("获取秘钥文件URL失败了");
            System.err.println(data.getMessage());
            return data;
        }
    }

    private List readKey() throws IOException {
        File file = (File)getFile("rsa.keystore").getResource();
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line;
        List<String> keyList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            keyList.add(line);
        }
        br.close();
        return keyList;
    }
}
