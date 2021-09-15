package io.github.hurynovich.base;

import lombok.AllArgsConstructor;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
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
    public boolean detect(IntegralImg integralImg, double valueFactor, Int2D pos){
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
