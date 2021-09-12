package io.github.hurynovich.base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlidingWindowTest {

    @Test
    void testNextWithStep1px(){
        int imgSize = 100;
        int dtctSize = 10;
        int step = 1;
        SlidingWindow sw = new SlidingWindow(new Int2D(imgSize, imgSize), new Int2D(dtctSize, dtctSize), step, step);

        int i = 0;
        while(sw.next() != null) i++;

        int slideSpace = imgSize - dtctSize;
        assertEquals(slideSpace * slideSpace, i);
    }

}