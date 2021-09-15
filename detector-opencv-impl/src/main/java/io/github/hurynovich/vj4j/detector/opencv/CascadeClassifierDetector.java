package io.github.hurynovich.vj4j.detector.opencv;

import io.github.hurynovich.base.*;
import io.github.hurynovich.vj4j.base.Detector;
import io.github.hurynovich.vj4j.base.Int2D;
import io.github.hurynovich.vj4j.base.Rect;
import lombok.AllArgsConstructor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CascadeClassifierDetector implements Detector {
    private final CascadeClassifier cascade;

    @Override
    public List<Rect> detect(BufferedImage img) {
        IntegralImg ii = IntegralImg.newIntegralImg(img);
        for (int y = 0; y < ii.getHigh(); y++){
            for (int x = 0; x < ii.getWidth(); x++){
                System.out.printf("%5d ", ii.getValue(x, y));
            }
            System.out.println();
        }
        IntegralImg sqIi = IntegralImg.newSquaredIntegralImg(img);
        SlidingWindow slider = new SlidingWindow(new Int2D(img.getWidth(), img.getHeight()), cascade.getWindowSize());
        List<Rect> result = new ArrayList<>();
        Int2D pos;
        while ((pos = slider.next()) != null) {
            double valueFactor = calcValueFactor(ii, sqIi, pos, cascade.getWindowSize());
            if(cascade.detect(ii, valueFactor, pos)) {
                result.add(new Rect(pos, pos.plus(cascade.getWindowSize())));
            }
        }

        return result;
    }

    private double calcValueFactor(IntegralImg ii, IntegralImg sqIi, Int2D pos, Int2D winSize) {
//        if(1==1){return 1;}

        var normWin = new Rect(
                pos.x + 1,
                pos.y + 1,
                pos.x + winSize.x - 1,
                pos.y + winSize.y -1
        );
        int valSum = ii.getSum(normWin); //CALC_SUM_OFS(nofs, pwinPtr);
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
