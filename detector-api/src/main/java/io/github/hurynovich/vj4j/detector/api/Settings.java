package io.github.hurynovich.vj4j.detector.api;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Settings {
    private Int2D   minObjectSize;
    private Int2D   maxObjectSize;
    private Integer minNeighbors;
    private Double  minOverlap;
}
