package io.github.hurynovich.vj4j.detector.api;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static java.lang.Math.max;
import static java.lang.Math.min;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
//TODO think to rename as Rectangle
public final class Rect {
    public final Int2D a;
    public final Int2D b;

    public Rect(int x1, int y1, int x2, int y2){
        a = new Int2D(x1, y1);
        b = new Int2D(x2, y2);
    }

    public int area(){
        return (b.x - a.x) * (b.y - a.y);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[(").append(a.x).append(", ").append(a.y).append("), ");
        sb.append("(").append(b.x).append(", ").append(b.y).append(")]");
        return sb.toString();
    }

    public Rect translate(Int2D shift) {
        return new Rect(a.plus(shift), b.plus(shift));
    }

    public static Rect intersection(Rect one, Rect two){
        int aX = max(one.a.x, two.a.x);
        int aY = max(one.a.y, two.a.y);
        int bX = min(one.b.x, two.b.x);
        int bY = min(one.b.y, two.b.y);

        if (aX > bX || aY > bY) return null;

        return new Rect(aX, aY, bX, bY);
    }

    public static Rect rect(int x1, int y1, int x2, int y2){
        return new Rect(x1, y1, x2, y2);
    }
}
