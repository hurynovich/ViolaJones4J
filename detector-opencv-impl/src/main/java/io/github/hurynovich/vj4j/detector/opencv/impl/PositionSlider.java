package io.github.hurynovich.vj4j.detector.opencv.impl;

import io.github.hurynovich.vj4j.detector.api.Point;

//TODO add validation for constructor args
public class PositionSlider {
    private final Point imgSz;
    private final Point spotSz;

    //TODO initialization for it
    private final int xStep;
    private final int yStep;

    private int x;
    private int y = 0;

    public PositionSlider(Point imgSz, Point spotSz, int xStep, int yStep){
        this.imgSz = imgSz;
        this.spotSz = spotSz;
        this.xStep = xStep;
        this.yStep = yStep;

        //it is done to return (0, 0) by #next() at first call
        this.x = -xStep;
    }
    public PositionSlider(Point imgSz, Point spotSz){
        this(imgSz, spotSz, 2, 2);
    }

    public Point next(){
        x += xStep;
        if(x + spotSz.x < imgSz.x) return new Point(x, y);

        x = 0;
        y += yStep;
        if(y + spotSz.y < imgSz.y) return new Point(x, y);

        return null;
    }
}
