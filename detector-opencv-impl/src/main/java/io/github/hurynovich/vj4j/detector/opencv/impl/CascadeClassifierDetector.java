package io.github.hurynovich.vj4j.detector.opencv.impl;

import io.github.hurynovich.vj4j.detector.api.Detector;
import io.github.hurynovich.vj4j.detector.api.Int2D;
import io.github.hurynovich.vj4j.detector.api.Rect;
import io.github.hurynovich.vj4j.detector.api.Settings;
import io.github.hurynovich.vj4j.detector.opencv.impl.util.AwtImageWrapper;
import lombok.AllArgsConstructor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static io.github.hurynovich.vj4j.detector.api.Int2D.int2D;
import static io.github.hurynovich.vj4j.detector.opencv.impl.util.Utils.correctMaxObjectSize;

@AllArgsConstructor
public class CascadeClassifierDetector implements Detector {
    private final CascadeClassifier cascade;

    @Override
    public List<Rect> detect(BufferedImage orgImg, Settings settings) {
        final var img = new AwtImageWrapper(orgImg);
        final var spotSz = cascade.getWindowSize();
        final var imgSz = int2D(img.getWidth(), img.getHeight());
        final var minObjSz = int2D(40, 40);
        final var maxObjSz = correctMaxObjectSize(spotSz, imgSz, imgSz).divide(4);

        List<Rect> result = new ArrayList<>();
        ScaleSlider scaleItr = new ScaleSlider(spotSz, minObjSz, maxObjSz, 1.1);
        while (scaleItr.hasNext()){
            double scale = scaleItr.next();
            Int2D scaledImgSz = imgSz.divide(scale);
            var scaledImg = AwtImageWrapper.scale(img, scaledImgSz);
            applyCascade(scaledImg, scale, result);
        }

        int minNeibors = 3;
        return new SimpleRectMerger(minNeibors, 10.0).merge(result);
    }

    private void applyCascade(AwtImageWrapper img, double scale, List<Rect> result) {
        IntegralImg ii = IntegralImg.newIntegralImg(img);
        IntegralImg sqIi = IntegralImg.newSquaredIntegralImg(img);
        PositionSlider slider = new PositionSlider(new Int2D(img.getWidth(), img.getHeight()), cascade.getWindowSize());

        Int2D pos;
        while ((pos = slider.next()) != null) {
            double valueFactor = calcValueFactor(ii, sqIi, pos, cascade.getWindowSize());
            if(cascade.detect(ii, valueFactor, pos)) {
                result.add(new Rect(
                        pos.multiply(scale),
                        pos.plus(cascade.getWindowSize()).multiply(scale)
                ));
            }
        }
    }

    private double calcValueFactor(IntegralImg ii, IntegralImg sqIi, Int2D pos, Int2D winSize) {
        var normWin = new Rect(
                pos.x + 1,
                pos.y + 1,
                pos.x + winSize.x - 1,
                pos.y + winSize.y -1
        );
        int valSum = ii.getSum(normWin);
        int valSqSum = sqIi.getSum (normWin);

        //площадь окна меньшего на 1 px по краю
        int winArea = normWin.area();
        double nf = (winArea * valSqSum) - (valSum * valSum);

        if (nf > 0) {
            nf = Math.sqrt(nf);
            return 1.0 / nf;
        } else {
            return  1.0;
        }
    }
}
