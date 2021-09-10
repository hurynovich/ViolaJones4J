package io.github.hurynovich.base;

import static io.github.hurynovich.base.Utils.calcGrayValue;

public class IntegralImg {
    private final int[][] data;
    private final int width;
    private final int  height;

    public int getValue(Int2D point){
        return data[point.y][point.x];
    }
    public int getValue(int x, int y){
        return data[y][x];
    }
    public int getSum(Rect rect){
        return 0;
    }
    public int getWidth(){return width;}
    public int getHigh(){return height;}

    IntegralImg(Image img){
        //allocate array to store integral image
        width = img.getWidth();
        height = img.getHeight();
        data = new int[height][];
        for(int i = 0; i < data.length; i++){
            data[i] = new int[width];
        }

        //initialize first row and first column of integral image
        data[0][0] = calcGrayValue(img.getRGB(0, 0));
        for (int x = 1; x < width; x++){
            data[0][x] = data[0][x - 1] + calcGrayValue(img.getRGB(x, 0));
        }
        for (int y = 1; y < height; y++){
            data[y][0] = data[y - 1][0] + calcGrayValue(img.getRGB(0, y));
        }

        //initialize the rest of integral image
        for (int y = 1; y < height; y++){
            int rowSum = calcGrayValue(img.getRGB(0, y));
            for (int x = 1; x < width; x++){
                rowSum += calcGrayValue(img.getRGB(x, y));
                data[y][x] = data[y-1][x] + rowSum;
            }
        }
    }
}
