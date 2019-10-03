package cn.akira.util;

import cn.akira.pojo.User;
import cn.akira.returnable.CommonData;
import org.apache.commons.lang.RandomStringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ValidateUtil {

    private static final char[] CODE_SEQUENCE= {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static CommonData UserFormDataValidate(User user){
        String emailReg = "^[a-z0-9A-Z]+[-|a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$";
        String chineseMainLandPhoneReg = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";
        String uname = user.getUname();
        String bindPhone = user.getBindPhone();
        String bindEmail = user.getBindEmail();
        String email = user.getUserInfo().getEmail();
        String addr = user.getUserInfo().getAddr();
        String realName = user.getRealNameAuth().getRealName();
        String cid = user.getRealNameAuth().getCid();
        String certType = user.getRealNameAuth().getCertType();

        if (uname == null) {
            return new CommonData("用户名是必填的哦~", "uname", false);
        } else if (uname.length() < 3) {
            return new CommonData("用户名至少3个字符", "uname", false);
        } else if (uname.contains(" ") || uname.contains("@")) {
            return new CommonData("用户名不能有空格或者艾特符", "uname", false);
        } else if (uname.matches(chineseMainLandPhoneReg)) {
            return new CommonData("不要用疑似手机号格式的用户名嘛", "uname", false);
        }

        if (bindPhone == null && bindEmail == null) {
            return new CommonData("邮箱和手机至少得绑一个吧", false);
        }

        if (bindPhone != null && !bindPhone.matches(chineseMainLandPhoneReg)) {
            return new CommonData("手机号格式不对", "bindPhone", false);
        }

        if (bindEmail != null && !bindEmail.matches(emailReg)) {
            return new CommonData("邮箱格式没写对", "bindEmail", false);
        }

        if (email != null && !email.matches(emailReg)) {
            return new CommonData("邮箱格式不正确", "email", false);
        }
        if (addr != null && (addr.replace(" ", "").length() < 5)) {
            return new CommonData("地址写详细点嘛", "addr", false);
        }
        if (!((/*都为空*/
                realName == null &&
                        cid == null &&
                        certType == null
        ) || (/*都不为空*/
                realName != null &&
                        cid != null &&
                        certType != null
        ))) {
            return new CommonData("如需实名信息,就请将其完善", false);
        } else if (cid != null && certType.equals("1") && !cid.matches("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$")) {
            return new CommonData("身份证格式不对", "cid", false);
        }
        return new CommonData();
    }

    public static Map<String,String> generateVerifyCode(String compilePath) throws IOException {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String fileName = RandomStringUtils.random(40, str);
        File path = new File(compilePath + "image\\temp\\verify_code");
        if (!path.exists() && !path.mkdirs()) {
            throw new IOException("Failed to create directory");
        }
        String filePath = path.toString() + "\\" + fileName + ".jpg";
        OutputStream out = new FileOutputStream(filePath);
        Map<String, Object> map = generateCodeAndPic();
        ImageIO.write((RenderedImage) map.get("codePic"), "jpeg", out);
        String code = map.get("code").toString();
        System.out.println("验证码：" + code);
        Map<String, String> result = new HashMap<>();
        result.put("fileName", fileName + ".jpg");
        result.put("verifyCode", code);
        return result;
    }

    @SuppressWarnings("DuplicatedCode")
    private static Map<String, Object> generateCodeAndPic() {
        // 定义图像buffer
        // 定义图片的width
        int width = 120;
        // 定义图片的height
        int height = 45;
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics gd = buffImg.getGraphics();
        // 创建一个随机数生成器类
        Random random = new Random();
        // 将图像填充为白色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);

        // 创建字体，字体的大小应该根据图片的高度来定。
        int fontHeight = 35;
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        // 设置字体
        gd.setFont(font);

        // 画边框
        gd.setColor(Color.WHITE);
        gd.drawRect(0, 0, width - 1, height - 1);

        gd.setColor(Color.RED);
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }
        gd.setColor(Color.BLUE);
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }
        gd.setColor(Color.YELLOW);
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }
        gd.setColor(Color.GREEN);
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }
        gd.setColor(Color.PINK);
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }
        gd.setColor(Color.CYAN);
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }

        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;

        // 随机产生codeCount数字的验证码。
        // 定义图片上显示验证码的个数
        int codeCount = 4;
        for (int i = 0; i < codeCount; i++) {
            String code = String.valueOf(CODE_SEQUENCE[random.nextInt(CODE_SEQUENCE.length)]);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            gd.setColor(new Color(red, green, blue));
            int xx = 22;
            int codeY = 35;
            gd.drawString(code, (i + 1) * xx, codeY);
            randomCode.append(code);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", randomCode);
        map.put("codePic", buffImg);
        return map;
    }
}
