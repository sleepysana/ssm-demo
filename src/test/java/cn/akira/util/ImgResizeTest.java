package cn.akira.util;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImgResizeTest {

    @Test
    public void selfAdapt() throws IOException {
        String imgPath = "D:\\Profiles\\Pictures\\TIM截图20190827151649.png";
        BufferedImage bi = ImageIO.read(new File(imgPath));
        int height = bi.getHeight();
        int width = bi.getWidth();
        System.out.println("图片尺寸:\t" + width + "*" + height);
        if (height < width) {
            BufferedImage subImage = bi.getSubimage((width - height) / 2, 0, height, height);
            ImageIO.write(subImage,"png",new File("D:\\output.png"));
        }else if (height > width){
            BufferedImage subImage = bi.getSubimage( 0,(height - width) / 2, width, width);
            ImageIO.write(subImage,"png",new File("D:\\output1.png"));
        }
    }
}
