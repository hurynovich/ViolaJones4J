package io.github.hurynovich.vj4j.detector.opencv.impl.util;

import io.github.hurynovich.vj4j.detector.api.Point;

import static java.lang.Math.round;

public final class Utils {

    private Utils(){}

    public static int calcGrayValue(int argb){
        return (int) round((getRed(argb) + getGreen(argb) + getBlue(argb)) / 3.0);
    }

    public static int luminance(int argb){
        var val = (0.299 * getRed(argb) + 0.587 * getGreen(argb) + 0.114 * getBlue(argb));
        return (int) round(val);
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

    public static Point correctMinObjectSize(Point spotSz, Point objSz){
        if(objSz.x <= spotSz.x || objSz.y <= spotSz.y) return spotSz;

        //object width or height must not be less than corresponding cascade window width or height
        double x = Math.max(spotSz.x, objSz.x);
        double y = Math.max(spotSz.y, objSz.y);

        //scale factor for width and height may be different, use smaller one
        double scale = Math.min(x / spotSz.x, y / spotSz.y);
        return spotSz.multiply(scale);
    }

    /**
     * TODO
     *
     * @param spotSz - native size of detecting window used in cascade classifier.
     * @param imgSz - size of image where object detection is performed.
     * @param objSz - upper size limit for object desired
     * @return upper size limit for object
     */
    public static Point correctMaxObjectSize(final Point spotSz, final Point imgSz, final Point objSz){
        // if both object dimensions are too small then cascade window size is used
        if(objSz.x <= spotSz.x && objSz.y <= spotSz.y) return spotSz;

        // ensure that any object dimension is not less than cascade window size
        double x = Math.max(spotSz.x, objSz.x);
        double y = Math.max(spotSz.y, objSz.y);

        //scale factor for width and height may be different, use grater one
        double scale = Math.max(x / spotSz.x, y / spotSz.y);

        //max possible scale of Spot to not oversize image
        double maxScale = Math.min(1.0 * imgSz.x / spotSz.x, 1.0 * imgSz.y / spotSz.y);

        //ensure scale not oversizes image
        scale = Math.min(scale, maxScale);
        return spotSz.multiply(scale);
    }
}
