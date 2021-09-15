package io.github.hurynovich.base;

import lombok.Setter;

@Setter
public class WeakHaarClassifierBuilder {
    private double threshold;
    private double leftValue;
    private double rightValue;
    private int featureIndex;
}
