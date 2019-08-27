package cn.akira.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImgResizeUtil {

    public static void selfAdapt(File imgFile) throws Exception {
        String filePathStr = imgFile.toPath().toString();
        String suffixWithOutDot = filePathStr.substring(filePathStr.lastIndexOf(".") + 1);
        System.out.println(filePathStr);
        BufferedImage bi = ImageIO.read(imgFile);
//        boolean delete = imgFile.delete();
//        System.out.println("删了吗?\t" + delete);
        int height = bi.getHeight();
        int width = bi.getWidth();
        System.out.println("图片尺寸:\t" + width + "*" + height);
        if (height < width) {
            bi = bi.getSubimage((width - height) / 2, 0, height, height);
        } else if (height > width) {
            bi = bi.getSubimage(0, (height - width) / 2, width, width);
        }
        boolean isAdapted = ImageIO.write(bi, suffixWithOutDot, imgFile);
        System.out.println("图片裁剪成功了吗?\t" + isAdapted);
    }
}