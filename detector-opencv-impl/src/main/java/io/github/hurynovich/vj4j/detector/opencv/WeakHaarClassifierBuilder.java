package io.github.hurynovich.vj4j.detector.opencv;

import lombok.Setter;

@Setter
public class WeakHaarClassifierBuilder {
    private double threshold;
    private double leftValue;
    private double rightValue;
    private int featureIndex;
}
