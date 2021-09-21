package io.github.hurynovich.vj4j.detector.opencv.impl;

import io.github.hurynovich.vj4j.detector.api.Point;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
class StrongHaarClassifier {
    private final List<WeakHaarClassifier> weakClassifiers;
    private final double threshold;

    /**
     *
     * @param integralImg
     * @param valueFactor
     * @param pos
     * @return {@code true} if object was detected; otherwise {@code false}.
     *
     */
    public boolean detect(IntegralImg integralImg, double valueFactor, Point pos){
        double sumValue = 0;
        for (var classifier : weakClassifiers){
            var val = classifier.calcValue(integralImg, valueFactor, pos);
            sumValue += val;
        }

        //TODO check unequation direction
        var result = sumValue > threshold;
        return  result;
    }

}
