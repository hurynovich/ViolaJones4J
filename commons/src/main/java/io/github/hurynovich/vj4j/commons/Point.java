package io.github.hurynovich.vj4j.commons;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static java.lang.Math.round;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
//TODO think to rename as Point
public final class Point implements io.github.hurynovich.vj4j.detector.api.Point {

    public static final Point ZERO = pointOf(0, 0);

    public final int x;
    public final int y;

    public Point plus(Point point) {
        return new Point(x + point.x, y + point.y);
    }

    public Point multiply(double multiplier) {
        return pointOf((int)round(x * multiplier), (int)round(y * multiplier));
    }

    public Point divide(double divider) {
        return pointOf((int)round(x / divider), (int)round(y / divider));
    }

    public Point multiply(int multiplier) {
        return pointOf(x * multiplier, y * multiplier);
    }

    @Override
    public String toString() {
        return "(x=" + x + ", y=" + y + ')';
    }

    public static Point pointOf(int x, int y){
        return new Point(x, y);
    }
}
