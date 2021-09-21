package io.github.hurynovich.vj4j.detector.api;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Settings {
    private Point minObjectSize;
    private Point maxObjectSize;
    private Integer minNeighbors;
    private Double  minOverlap;
}
