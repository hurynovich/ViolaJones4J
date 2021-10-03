package io.github.hurynovich.vj4j.detector.opencv.impl.util;

import io.github.hurynovich.vj4i.image.api.ImageEditor;
import io.github.hurynovich.vj4j.core.api.Image;
import io.github.hurynovich.vj4j.core.api.Point;

import java.util.ServiceLoader;

public final class ImageUtils {
    //TODO try find other solution
    public static Image scale(Image source, Point targetSz) {
        var loader = ServiceLoader.load(ImageEditor.class);
        for (var editor : loader){
            if(editor.canEdit(source)) return editor.scale(source, targetSz.getX(), targetSz.getY());
        }

        throw new UnsupportedOperationException("There is no service capable to scale this Image implementation.");
    }
}
