package io.github.hurynovich.vj4j.detector.opencv.impl.util;

import io.github.hurynovich.vj4j.detector.api.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.github.hurynovich.vj4j.detector.api.Point.pointOf;
import static io.github.hurynovich.vj4j.detector.opencv.impl.util.Utils.correctMaxObjectSize;
import static io.github.hurynovich.vj4j.detector.opencv.impl.util.Utils.correctMinObjectSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

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

    /**
     * Check cases when user provides --min-object-size less than cascade native spot size.
     */
    //TODO use parametrized test
    @Test
    void testMinSpotIsLessThenNativeSpot() {
        Point nativeSpot = new Point(10, 13);
        Point minSpot = new Point(3, 11);
        Point minSpotX = new Point(3, 20);
        Point minSpotY = new Point(20, 11);

        //ensure that already existing instance returned
        assertTrue(nativeSpot == correctMinObjectSize(nativeSpot, minSpot));
        assertTrue(nativeSpot == correctMinObjectSize(nativeSpot, minSpotX));
        assertTrue(nativeSpot == correctMinObjectSize(nativeSpot, minSpotY));
    }

    //TODO use parametrized test
    @Test
    void testMinSpotAspectRatioCorrected() {
        Point nativeSpot = new Point(5, 7);
        Point minSpotByX = new Point(10, 1000);
        Point minSpotByY = new Point(1000, 21);

        var expected = new Point(10, 7*2);
        var result = correctMinObjectSize(nativeSpot, minSpotByX);
        assertEquals(expected, result);

        expected = new Point(5*3, 21);
        result = correctMinObjectSize(nativeSpot, minSpotByY);
        assertEquals(expected, result);
    }

    static Stream<Arguments> correctMaxSpotSizeProvider(){
        final var spotSz = pointOf(5, 15);
        final var maxSpotSz = pointOf(47, 140);
        final var imgSz = pointOf(90, 140);

        return Stream.of(
                arguments(spotSz, imgSz, pointOf(10, 2), pointOf(10, 30), "Case when object height less then spot height."),
                arguments(spotSz, imgSz, pointOf(2, 45), pointOf(15, 45), "Case when object width less then spot width."),
                arguments(spotSz, imgSz, pointOf(1, 2), pointOf(5, 15), "Case when object width and height less then spot ones."),
                arguments(spotSz, imgSz, pointOf(100, 2), maxSpotSz, "Case when object width greater then image width."),
                arguments(spotSz, imgSz, pointOf(10, 150), maxSpotSz, "Case when object height greater then image height."),
                arguments(spotSz, imgSz, pointOf(100, 150), maxSpotSz, "Case when Object size is greater then Image size.")
        );
    }

    @ParameterizedTest
    @MethodSource("correctMaxSpotSizeProvider")
    void testCorrectMaxSpotSize(Point spotSz, Point imgSz, Point objectSize, Point expected, String msg){
        var result = correctMaxObjectSize(spotSz, imgSz, objectSize);
        assertEquals(expected, result, msg);

        double expectedAspectRation = 1.0 * spotSz.x / spotSz.y;
        double resultAspectRation = 1.0 * result.x / result.y;
        double delta = 0.005;
        assertEquals(expectedAspectRation, resultAspectRation, delta, "Object aspect ratio must be equal to Spot aspect ratio.");

        assertTrue(result.x >= spotSz.x, "Object width must not be less then Spot width.");
        assertTrue(result.y >= spotSz.y, "Object height must not be less then Spot height.");
        assertTrue(result.x <= imgSz.x, "Object width must not be greater then Image width.");
        assertTrue(result.y <= imgSz.y, "Object height must not be greater then Image height.");
    }
}