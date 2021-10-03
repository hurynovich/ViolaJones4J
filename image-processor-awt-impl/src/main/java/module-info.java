import io.github.hurynovich.vj4i.image.api.ImageEditor;
import io.github.hurynovich.vj4i.image.awt.impl.ImageEditorImpl;

module vj4j.image.processor.awt.impl {
    requires java.desktop;
    requires vj4j.image.processor.api;

    provides ImageEditor with ImageEditorImpl;
}