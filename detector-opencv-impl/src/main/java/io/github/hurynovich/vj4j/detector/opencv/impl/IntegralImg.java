package io.github.hurynovich.vj4j.detector.opencv.impl;

import io.github.hurynovich.vj4j.detector.api.Int2D;
import io.github.hurynovich.vj4j.detector.api.Rect;

import java.awt.image.BufferedImage;
import java.util.function.IntUnaryOperator;

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
            int two = getValue(x2, y1);
            int three = getValue(x1, y2);
            int four = getValue(x2, y2);
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
        width = img.getWidth() + 1;
        height = img.getHeight() + 1;
        data = new int[height][];
        for(int i = 0; i < data.length; i++){
            data[i] = new int[width];
        }

        //initialize first row and first column of integral image
        data[1][1] = pixelValCalculator.applyAsInt(img.getRGB(0, 0));
        for (int x = 1; x < width; x++){
            data[1][x] = data[1][x - 1] + pixelValCalculator.applyAsInt(img.getRGB(x - 1, 0));
        }
        for (int y = 1; y < height; y++){
            data[y][1] = data[y - 1][1] + pixelValCalculator.applyAsInt(img.getRGB(0, y - 1));
        }

        //initialize the rest of integral image
        for (int y = 1; y < height - 1; y++){
            int rowSum = pixelValCalculator.applyAsInt(img.getRGB(0, y));
            for (int x = 1; x < width - 1; x++){
                rowSum += pixelValCalculator.applyAsInt(img.getRGB(x, y));
                data[y+1][x+1] = data[y][x+1] + rowSum;
            }
        }
    }

    public static IntegralImg newIntegralImg(BufferedImage img) {
        return new IntegralImg(img, Utils::luminance);
    }

    public static IntegralImg newSquaredIntegralImg(BufferedImage img) {

        IntUnaryOperator f = (int rgb) -> {
            int gray = Utils.luminance(rgb);
            return gray * gray;
        };
        return new IntegralImg(img, f);
    }
}
