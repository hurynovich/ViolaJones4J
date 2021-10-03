package io.github.hurynovich.vj4j.detector.opencv.impl;

import io.github.hurynovich.vj4j.commons.Utils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class IntegralImgImplTest {
    //FIXME
    /*
    @Test
    @Disabled
    void testImageReading() throws Exception {
//        BufferedImage img = ImageIO.read(IntegralImgImplTest.class.getResourceAsStream("/test-image.png"));

        int x, y = 0;
        while (y < img.getHeight()) {
            x = 0;
            while (x < img.getWidth()) {
                int val = Utils.calcGrayValue(img.getRGB(x, y));
                System.out.print(val > 127 ? "x" : "_");
                x++;
            }
            System.out.println();
            y++;
        }
    }

    @Test
    @Disabled
    void testIntegralImageInitialization() {
        BufferedImage srcImg = new BufferedImage(4,3, BufferedImage.TYPE_INT_RGB);
        srcImg.getRaster().setPixel(0,0, new int[]{2,2,2});
        srcImg.getRaster().setPixel(1,0, new int[]{4,4,4});
        srcImg.getRaster().setPixel(2,0, new int[]{7,7,7});
        srcImg.getRaster().setPixel(3,0, new int[]{1,1,1});

        srcImg.getRaster().setPixel(0,1, new int[]{9,9,9});
        srcImg.getRaster().setPixel(1,1, new int[]{3,3,3});
        srcImg.getRaster().setPixel(2,1, new int[]{5,5,5});
        srcImg.getRaster().setPixel(3,1, new int[]{1,1,1});

        srcImg.getRaster().setPixel(0,2, new int[]{7,7,7});
        srcImg.getRaster().setPixel(1,2, new int[]{2,2,2});
        srcImg.getRaster().setPixel(2,2, new int[]{5,5,5});
        srcImg.getRaster().setPixel(3,2, new int[]{7,7,7});

        IntegralImg ii = IntegralImg.newIntegralImg(new AwtImageWrapper(srcImg));

        System.out.println("Integral image:");
        for (int y = 0; y < ii.getHigh(); y++){
            for (int x = 0; x < ii.getWidth(); x++){
                System.out.printf("%3d ", ii.getValue(x, y));
            }
            System.out.println();
        }
    }

    @Test
    @Disabled
    void testIntegralImageAreaValue() {
        BufferedImage srcImg = new BufferedImage(4,3, BufferedImage.TYPE_INT_RGB);
        srcImg.getRaster().setPixel(0,0, new int[]{2,2,2});
        srcImg.getRaster().setPixel(1,0, new int[]{4,4,4});
        srcImg.getRaster().setPixel(2,0, new int[]{7,7,7});
        srcImg.getRaster().setPixel(3,0, new int[]{1,1,1});

        srcImg.getRaster().setPixel(0,1, new int[]{9,9,9});
        srcImg.getRaster().setPixel(1,1, new int[]{3,3,3});
        srcImg.getRaster().setPixel(2,1, new int[]{5,5,5});
        srcImg.getRaster().setPixel(3,1, new int[]{1,1,1});

        srcImg.getRaster().setPixel(0,2, new int[]{7,7,7});
        srcImg.getRaster().setPixel(1,2, new int[]{2,2,2});
        srcImg.getRaster().setPixel(2,2, new int[]{5,5,5});
        srcImg.getRaster().setPixel(3,2, new int[]{7,7,7});

        IntegralImg ii = IntegralImg.newIntegralImg(new AwtImageWrapper(srcImg));

        System.out.println("Integral image:");
        for (int y = 0; y < ii.getHigh(); y++){
            for (int x = 0; x < ii.getWidth(); x++){
                System.out.printf("%3d ", ii.getValue(x, y));
            }
            System.out.println();
        }

        System.out.println(ii.getSum(0, 1, 1, 2));
    }
    */
}
