import io.github.hurynovich.base.Utils;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class IntegralImgImplTest {
    @Test
    void testImageReading() throws Exception {
        BufferedImage img = ImageIO.read(IntegralImgImplTest.class.getResourceAsStream("/test-image.png"));

        int x, y = 0;
        while (y < img.getHeight()){
            x = 0;
            while (x < img.getWidth()){
                int val = Utils.calcGrayValue(img.getRGB(x, y));
                System.out.print(val > 127 ? "x" : "_");
                x++;
            }
            System.out.println();
            y++;
        }
    }
}
