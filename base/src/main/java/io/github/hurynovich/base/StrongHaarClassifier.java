package io.github.hurynovich.base;

import java.util.ArrayList;
import java.util.List;


public class StrongHaarClassifier {
    private double threshold;
    private List<WeakHaarClassifier> weakClassifiers = new ArrayList<>();

    public boolean doDetection(Image image){
        double sumValue = 0;
        for (var classifier : weakClassifiers){
            sumValue += classifier.calcValue(image);
        }
        return sumValue < threshold;
    }
}
