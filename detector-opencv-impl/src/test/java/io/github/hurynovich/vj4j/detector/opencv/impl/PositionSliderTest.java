package io.github.hurynovich.vj4j.detector.opencv.impl;

import io.github.hurynovich.vj4j.commons.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionSliderTest {

    @Test
    void testNextWithStep1px(){
        int imgSize = 100;
        int dtctSize = 10;
        int step = 1;
        PositionSlider sw = new PositionSlider(new Point(imgSize, imgSize), new Point(dtctSize, dtctSize), step, step);

        int i = 0;
        while(sw.next() != null) i++;

        int slideSpace = imgSize - dtctSize;
        assertEquals(slideSpace * slideSpace, i);
    }

}