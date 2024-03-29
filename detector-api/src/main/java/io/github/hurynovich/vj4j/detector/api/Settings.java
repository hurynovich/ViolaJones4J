package io.github.hurynovich.vj4j.detector.api;

import io.github.hurynovich.vj4j.core.api.Point;
import lombok.Data;

@Data
public class Settings {
    private Point minObjectSize;
    private Point maxObjectSize;
    private Integer minNeighbors;
    private Double  minOverlap;
}
