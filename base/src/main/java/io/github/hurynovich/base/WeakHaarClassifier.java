package io.github.hurynovich.base;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WeakHaarClassifier {
    private double threshold;
    private double leftValue;
    private double rightValue;
    private HaarFeature feature;

    public double calcValue(IntegralImg image, Int2D shift){
        return (feature.calcValue(image, shift) < threshold) ? leftValue : rightValue;
    }
}
