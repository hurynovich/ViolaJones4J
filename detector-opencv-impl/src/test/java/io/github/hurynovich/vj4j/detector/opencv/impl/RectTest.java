package io.github.hurynovich.vj4j.detector.opencv.impl;

import io.github.hurynovich.vj4j.detector.api.Rect;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectTest {
    @Test
    void testToString(){
        Rect r = new Rect(1,2, 3, 4);
        assertEquals("(1, 2, 3, 4)", r.toString());
    }

}