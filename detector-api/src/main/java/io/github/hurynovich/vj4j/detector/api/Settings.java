package io.github.hurynovich.vj4j.detector.api;

import lombok.Data;

@Data
public class Settings {
    private Point minObjectSize;
    private Point maxObjectSize;
    private Integer minNeighbors;
    private Double  minOverlap;
}
