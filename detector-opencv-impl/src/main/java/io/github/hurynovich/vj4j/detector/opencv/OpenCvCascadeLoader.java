package io.github.hurynovich.vj4j.detector.opencv;

import io.github.hurynovich.base.Detector;
import io.github.hurynovich.base.DetectorLoader;

import javax.xml.parsers.SAXParserFactory;
import java.nio.file.Files;
import java.nio.file.Path;

public class OpenCvCascadeLoader implements DetectorLoader {
    private SAXParserFactory factory = SAXParserFactory.newInstance();

    @Override
    public boolean canLoad(Path path) {
        //TODO implement
        return true;
    }

    @Override
    public Detector load(Path path) {
        if(!Files.exists(path)){
            //TODO create custom exception
            throw new RuntimeException("File '" + path.toAbsolutePath() + "' does not exist.");
        }

        OpenCvHandler openCvHandler = new OpenCvHandler();
        try {
            var parser = factory.newSAXParser();
            parser.parse(path.toFile(), openCvHandler);
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
        }

        return openCvHandler.getDetector();
    }
}
