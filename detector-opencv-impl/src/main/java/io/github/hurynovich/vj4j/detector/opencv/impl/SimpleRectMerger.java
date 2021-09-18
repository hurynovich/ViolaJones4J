package io.github.hurynovich.vj4j.detector.opencv.impl;

import io.github.hurynovich.vj4j.detector.api.Rect;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Absolutely not optimised algorithm created as quick temp solution.
 */
@RequiredArgsConstructor
//TODO find better name
public final class SimpleRectMerger implements RectMerger {

    private final int minSections;
    private final double minOverlap;

    @Override
    public List<Rect> merge(List<Rect> rects) {
        List<RectGroup> rectGroups = new ArrayList<>();
        for (Rect r : rects) {
            dispatchRectangle(rectGroups, r);
        }

        //filter groups and convert to rectangles
        List<Rect> result = rectGroups.stream()
                .filter( i -> i.count() >= minSections)
                .map(RectGroup::calcCommon)
                .collect(toList());

        return result;
    }

    private void dispatchRectangle(List<RectGroup> rectGroups, Rect rect) {
        int foundGroups = 0;
        for (var group : rectGroups){
            if(group.addSection(rect))   {
                foundGroups++;
            }
        }

        // create new group for this rectangle
        if(foundGroups == 0) {
            rectGroups.add(new RectGroup(rect, minOverlap));
        }
    }
}
