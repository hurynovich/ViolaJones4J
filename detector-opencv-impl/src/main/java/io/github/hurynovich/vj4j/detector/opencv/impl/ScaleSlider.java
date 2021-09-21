package io.github.hurynovich.vj4j.detector.opencv.impl;

import io.github.hurynovich.vj4j.detector.api.Point;

public class ScaleSlider {
    private final double maxScale;
    private final double scaleStep;

    private double next = 1.0;

    public ScaleSlider(Point spotSz, Point minObjSz, Point maxObjSz, double scaleStep) {
        //TODO use better exception
        if(scaleStep <= 1.0) throw new RuntimeException();
        this.scaleStep = scaleStep;
        maxScale = Math.max(
                ((double)maxObjSz.x) / spotSz.x,
                ((double)maxObjSz.y) / spotSz.y
        );
        next = Math.min(
                ((double)minObjSz.x) / spotSz.x,
                ((double)minObjSz.y) / spotSz.y
        );
    }

    public boolean hasNext(){
        return next <= maxScale;
    }

    public double next(){
        //TODO use better exception
        if(!hasNext()) throw new RuntimeException();
        double result = next;
        next *= scaleStep;
        return result;
    }
}
