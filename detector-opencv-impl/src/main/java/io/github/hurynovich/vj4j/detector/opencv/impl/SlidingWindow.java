package io.github.hurynovich.vj4j.detector.opencv.impl;

import io.github.hurynovich.vj4j.detector.api.Int2D;

//TODO add validation for constructor args
public class SlidingWindow {
    private final Int2D imageSize;
    private final Int2D detectorSize;

    //TODO initialization for it
    private final int xStep;
    private final int yStep;

    private int x;
    private int y = 0;

    public SlidingWindow(Int2D imageSize, Int2D detectorSize, int xStep, int yStep){
        this.imageSize = imageSize;
        this.detectorSize = detectorSize;
        this.xStep = xStep;
        this.yStep = yStep;

        //it is done to return (0, 0) by #next() at first call
        this.x = -xStep;
    }
    public SlidingWindow(Int2D imageSize, Int2D detectorSize){
        this(imageSize, detectorSize, 2, 2);
    }

    public Int2D next(){
        x += xStep;
        if(x + detectorSize.x < imageSize.x) return new Int2D(x, y);

        x = 0;
        y += yStep;
        if(y + detectorSize.y < imageSize.y) return new Int2D(x, y);

        return null;
    }
}
