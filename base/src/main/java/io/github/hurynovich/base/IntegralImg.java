package io.github.hurynovich.base;

import java.awt.image.BufferedImage;
import java.util.function.IntUnaryOperator;

import static io.github.hurynovich.base.Utils.calcGrayValue;

public class IntegralImg {
    private final int[][] data;
    private final int width;
    private final int  height;

    public final int getValue(Int2D point){
        return data[point.y][point.x];
    }
    public int getValue(int x, int y){
        return data[y][x];
    }

    public int getSum(int x1, int y1, int x2, int y2){
        try {
            int one = getValue(x1, y1);
            int four = getValue(x2, y2);
            int three = getValue(x1, y2);
            int two = getValue(x2, y1);
            return one + four - two - three;
        } catch (Exception e) {
            return 0;
        }
    }

    public int getSum(Rect area){
        return getSum(area.a.x, area.a.y, area.b.x, area.b.y);
    }
    public int getWidth(){return width;}
    public int getHigh(){return height;}


    IntegralImg(BufferedImage img, IntUnaryOperator pixelValCalculator){
        //allocate array to store integral image
        width = img.getWidth();
        height = img.getHeight();
        data = new int[height][];
        for(int i = 0; i < data.length; i++){
            data[i] = new int[width];
        }

        //initialize first row and first column of integral image
        data[0][0] = pixelValCalculator.applyAsInt(img.getRGB(0, 0));
        for (int x = 1; x < width; x++){
            data[0][x] = data[0][x - 1] + pixelValCalculator.applyAsInt(img.getRGB(x, 0));
        }
        for (int y = 1; y < height; y++){
            data[y][0] = data[y - 1][0] + pixelValCalculator.applyAsInt(img.getRGB(0, y));
        }

        //initialize the rest of integral image
        for (int y = 1; y < height; y++){
            int rowSum = pixelValCalculator.applyAsInt(img.getRGB(0, y));
            for (int x = 1; x < width; x++){
                rowSum += pixelValCalculator.applyAsInt(img.getRGB(x, y));
                data[y][x] = data[y-1][x] + rowSum;
            }
        }
    }

    public static IntegralImg newIntegralImg(BufferedImage img) {
        return new IntegralImg(img, Utils::luminance);
    }

    public static IntegralImg newSquaredIntegralImg(BufferedImage img) {

        IntUnaryOperator f = (int rgb) -> {
            int gray = calcGrayValue(rgb);
            return gray * gray;
        };
        return new IntegralImg(img, f);
    }
}
