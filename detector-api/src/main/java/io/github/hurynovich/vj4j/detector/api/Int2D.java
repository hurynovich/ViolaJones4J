package io.github.hurynovich.vj4j.detector.api;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static java.lang.Math.round;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public final class Int2D {

    public static final Int2D ZERO = int2D(0, 0);

    public final int x;
    public final int y;

    public Int2D plus(Int2D point) {
        return new Int2D(x + point.x, y + point.y);
    }

    public Int2D multiply(double multiplier) {
        return int2D((int)round(x * multiplier), (int)round(y * multiplier));
    }

    public Int2D divide(double divider) {
        return int2D((int)round(x / divider), (int)round(y / divider));
    }

    public Int2D multiply(int multiplier) {
        return int2D(x * multiplier, y * multiplier);
    }

    @Override
    public String toString() {
        return "(x=" + x + ", y=" + y + ')';
    }

    public static Int2D int2D(int x, int y){
        return new Int2D(x, y);
    }
}
