package io.github.hurynovich.vj4j.detector.opencv.impl;

import io.github.hurynovich.vj4j.commons.Rect;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.github.hurynovich.vj4j.commons.Rect.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RectTest {
    @Test
    void testToString(){
        Rect r = new Rect(1, 2, 3, 4);
        assertEquals("[(1, 2), (3, 4)]", r.toString());
    }

    static Stream<Arguments> sourceIntersection(){
        return Stream.of(
                arguments(rect(0, 10, 20, 30), rect(10, 0, 30, 20), rect(10, 10, 20, 20), "Top Left"),
                arguments(rect(0, 10, 20, 20), rect(10, 0, 30, 30), rect(10, 10, 20, 20), "Left"),
                arguments(rect(0, 0, 20, 20), rect(10, 10, 30, 30), rect(10, 10, 20, 20), "Bottom Left"),
                arguments(rect(10, 10, 20, 20), rect(0, 0, 30, 30), rect(10, 10, 20, 20), "Inside"),
                arguments(rect(0, 0, 10, 10), rect(20, 20, 30, 30), null, "No intersection")
                //TODO add other cases
        );
    }

    @ParameterizedTest
    @MethodSource("sourceIntersection")
    void testIntersection(Rect one, Rect two, Rect expected, String msg){
        var result = intersection(one, two);

        assertEquals(expected, result, msg);
    }

}