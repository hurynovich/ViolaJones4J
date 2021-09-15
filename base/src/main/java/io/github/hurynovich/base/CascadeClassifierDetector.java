package io.github.hurynovich.base;

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
        for (int y = 0; y < ii.getWidth(); y++){
            for (int x = 0; x < ii.getHigh(); x++){
                System.out.printf("%5d ", ii.getValue(x, y));
            }
            System.out.println();
        }
        IntegralImg squaredIi = IntegralImg.newSquaredIntegralImg(img);
        SlidingWindow slider = new SlidingWindow(new Int2D(img.getWidth(), img.getHeight()), cascade.getWindowSize());
        List<Rect> result = new ArrayList<>();
        Int2D pos;
        while ((pos = slider.next()) != null) {
            double valueFactor = calcValueFactor(ii, squaredIi, pos, cascade.getWindowSize());
            if(cascade.detect(ii, valueFactor, pos)) {
                result.add(new Rect(pos, pos.plus(cascade.getWindowSize())));
            }
        }

        return result;
    }

    private double calcValueFactor(IntegralImg ii, IntegralImg sqIi, Int2D pos, Int2D winSize) {
        if(1==1){return 1;}

        var window = new Rect(pos, pos.plus(winSize));
        int valSum = ii.getSum(window); //CALC_SUM_OFS(nofs, pwinPtr);
        int valSqSum = sqIi.getSum (window);

        //площадь окна меньшего на 1 px по краю
        double winArea = winSize.x * winSize.y;
        double nf = (winArea * valSqSum) - (valSum * valSum);

        if (nf > 0) {
            nf = Math.sqrt(nf);
            return 1.0 / nf;
        } else {
            return  1.0;
        }
    }
}
