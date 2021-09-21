package io.github.hurynovich.vj4j.detector.opencv.impl;

import io.github.hurynovich.vj4j.detector.api.Detector;
import io.github.hurynovich.vj4j.detector.api.Point;
import io.github.hurynovich.vj4j.detector.api.Rect;
import io.github.hurynovich.vj4j.detector.api.Settings;
import io.github.hurynovich.vj4j.detector.opencv.impl.util.AwtImageWrapper;
import lombok.AllArgsConstructor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static io.github.hurynovich.vj4j.detector.api.Point.pointOf;
import static io.github.hurynovich.vj4j.detector.opencv.impl.util.Utils.correctMaxObjectSize;

@AllArgsConstructor
public class CascadeClassifierDetector implements Detector {
    private final CascadeClassifier cascade;

    @Override
    public List<Rect> detect(BufferedImage orgImg, Settings settings) {
        final var img = new AwtImageWrapper(orgImg);
        final var spotSz = cascade.getWindowSize();
        final var imgSz = pointOf(img.getWidth(), img.getHeight());
        final var minObjSz = pointOf(40, 40);
        final var maxObjSz = correctMaxObjectSize(spotSz, imgSz, imgSz).divide(4);
        final int minSections = 3;
        final double minOverlap = 10.0;

        List<Rect> result = new ArrayList<>();
        ScaleSlider scaleItr = new ScaleSlider(spotSz, minObjSz, maxObjSz, 1.1);
        while (scaleItr.hasNext()){
            double scale = scaleItr.next();
            Point scaledImgSz = imgSz.divide(scale);
            var scaledImg = AwtImageWrapper.scale(img, scaledImgSz);
            applyCascade(scaledImg, scale, result);
        }

        return new SimpleRectMerger(minSections, minOverlap).merge(result);
    }

    private void applyCascade(AwtImageWrapper img, double scale, List<Rect> result) {
        IntegralImg ii = IntegralImg.newIntegralImg(img);
        IntegralImg sqIi = IntegralImg.newSquaredIntegralImg(img);
        PositionSlider slider = new PositionSlider(new Point(img.getWidth(), img.getHeight()), cascade.getWindowSize());

        Point spotPos;
        while ((spotPos = slider.next()) != null) {
            double valueFactor = calcValueFactor(ii, sqIi, spotPos, cascade.getWindowSize());
            if(cascade.detect(ii, valueFactor, spotPos)) {
                result.add(new Rect(
                        spotPos.multiply(scale),
                        spotPos.plus(cascade.getWindowSize()).multiply(scale)
                ));
            }
        }
    }

    private double calcValueFactor(IntegralImg ii, IntegralImg sqIi, Point spotPos, Point spotSz) {
        var inside = new Rect(
                spotPos.x + 1,
                spotPos.y + 1,
                spotPos.x + spotSz.x - 1,
                spotPos.y + spotSz.y -1
        );
        int valSum = ii.getSum(inside);
        int valSqSum = sqIi.getSum (inside);

        int insideArea = inside.area();
        double normFactor = (insideArea * valSqSum) - (valSum * valSum);

        if (normFactor > 0) {
            return 1.0 / Math.sqrt(normFactor);
        } else {
            return  1.0;
        }
    }
}
