package io.github.hurynovich.vj4j.detector.opencv.impl;

import io.github.hurynovich.vj4j.base.Rect;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class Utils {

    private Utils(){}

    public static int calcGrayValue(int argb){
        return (int) Math.round((getRed(argb) + getGreen(argb) + getBlue(argb)) / 3.0);
    }

    public static int luminance(int argb){
        var val = (0.299 * getRed(argb) + 0.587 * getGreen(argb) + 0.114 * getBlue(argb));
        return (int) Math.round(val);
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
