package io.github.hurynovich.vj4j.detector.opencv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    private final int argb = 0xFF_AA_BB_CC;

    @Test
    void getRedTest(){
        int redVal =  Utils.getRed(argb);
        assertEquals(0xAA, redVal);
    }

    @Test
    void getGreenTest(){
        assertEquals(0xBB, Utils.getGreen(argb));
    }

    @Test
    void getBlueTest(){
        int blueVal =  Utils.getBlue(argb);
        assertEquals(0xCC, blueVal);
    }

    @Test
    void getAlphaTest(){
        assertEquals(0xFF, Utils.getAlpha(argb));
    }

}