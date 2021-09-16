package io.github.hurynovich.vj4j.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class Int2D {
    public final int x;
    public final int y;

    public Int2D plus(Int2D point) {
        return new Int2D(x + point.x, y + point.y);
    }

    @Override
    public String toString() {
        return "(x=" + x + ", y=" + y + ')';
    }
}
