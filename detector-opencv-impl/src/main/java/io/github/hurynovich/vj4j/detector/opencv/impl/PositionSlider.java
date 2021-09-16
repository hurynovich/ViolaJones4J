package io.github.hurynovich.vj4j.detector.opencv.impl;

import io.github.hurynovich.vj4j.detector.api.Int2D;

//TODO add validation for constructor args
public class PositionSlider {
    private final Int2D imgSz;
    private final Int2D spotSz;

    //TODO initialization for it
    private final int xStep;
    private final int yStep;

    private int x;
    private int y = 0;

    public PositionSlider(Int2D imgSz, Int2D spotSz, int xStep, int yStep){
        this.imgSz = imgSz;
        this.spotSz = spotSz;
        this.xStep = xStep;
        this.yStep = yStep;

        //it is done to return (0, 0) by #next() at first call
        this.x = -xStep;
    }
    public PositionSlider(Int2D imgSz, Int2D spotSz){
        this(imgSz, spotSz, 2, 2);
    }

    public Int2D next(){
        x += xStep;
        if(x + spotSz.x < imgSz.x) return new Int2D(x, y);

        x = 0;
        y += yStep;
        if(y + spotSz.y < imgSz.y) return new Int2D(x, y);

        return null;
    }
}
