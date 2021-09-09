package io.github.hurynovich.base;

public final class Utils {

    private Utils(){}

    public static int calcGrayValue(int argb){
        return (getRed(argb) + getGreen(argb) + getBlue(argb)) / 3;
    }

    public static int getRed(int rgb){
        return (rgb >> 16) & 0xFF;
    }

    public static int getGreen(int rgb){
        return (rgb >> 8) & 0xFF;
    }

    public static int getBlue(int argb){
        return argb & 0xFF;
    }

    public static int getAlpha(int argb) {
        return (argb >> 24) & 0xFF;
    }
}
