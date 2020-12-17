package io.github.hurynovich.violajones;

import java.util.ArrayList;
import java.util.List;

final class HaarFeature {

    private final List<FeaturePart> parts = new ArrayList<>();

    public class FeaturePart {
        private Rect area;

        /**
         * Defines whether related rectangle considered as "White" or "Black".
         * This factor is used when calculated value of whole feature.
         * TODO
         */
        private double impact;
    }
}
