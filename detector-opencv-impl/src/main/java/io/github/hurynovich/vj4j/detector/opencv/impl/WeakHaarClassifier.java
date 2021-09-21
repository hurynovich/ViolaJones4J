package io.github.hurynovich.vj4j.detector.opencv.impl;

import io.github.hurynovich.vj4j.detector.api.Point;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WeakHaarClassifier {
    private double threshold;
    private double leftValue;
    private double rightValue;
    private HaarFeature feature;

    public double calcValue(IntegralImg image, double valueFactor, Point shift){
        return (feature.calcValue(image, shift) * valueFactor < threshold) ? leftValue : rightValue;
    }
}
