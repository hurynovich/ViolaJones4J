package io.github.hurynovich.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
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
        sb.append("(");
        sb.append(a.x).append(", ");
        sb.append(a.y).append(", ");
        sb.append(b.x).append(", ");
        sb.append(b.y).append(")");

        return sb.toString();
    }

    public Rect translate(Int2D shift) {
        return new Rect(a.plus(shift), b.plus(shift));
    }
}
