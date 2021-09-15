package io.github.hurynovich.vj4j.detector.opencv;

import io.github.hurynovich.base.Detector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

class OpenCvHandler extends DefaultHandler {

    private final String SUPPORTED_STAGE_TYPE = "BOOST";
    private final List<String> XPATH_TO_FEATURE_PART = List.of("opencv_storage", "cascade", "features", "_", "rects", "_");
    private final List<String> XPATH_TO_FEATURE = List.of("opencv_storage", "cascade", "features", "_");
    private final List<String> XPATH_TO_STAGE = List.of("opencv_storage", "cascade", "stages", "_");
    private final List<String> XPATH_TO_STAGE_THRESHOLD = List.of("opencv_storage", "cascade", "stages", "_", "stageThreshold");
    private final List<String> XPATH_TO_WEAK_CLASSIFIER = List.of("opencv_storage", "cascade", "stages", "_", "weakClassifiers", "_");
    private final List<String> XPATH_TO_CLASSIFIER_NODE = List.of("opencv_storage", "cascade", "stages", "_", "weakClassifiers", "_", "internalNodes");
    private final List<String> XPATH_TO_CLASSIFIER_LEAF = List.of("opencv_storage", "cascade", "stages", "_", "weakClassifiers", "_", "leafValues");
    private final List<String> XPATH_TO_WINDOW_HEIGHT = List.of("opencv_storage", "cascade", "height");
    private final List<String> XPATH_TO_WINDOW_WIDTH = List.of("opencv_storage", "cascade", "width");


    private final List<String> xPath = new LinkedList<>();
    private final HaarCascadeClassifierBuilder builder = new HaarCascadeClassifierBuilder();
    private StringBuilder textBuffer = new StringBuilder();
    private Detector detector;

    @Override
    public void startDocument() throws SAXException {
        xPath.clear();
        builder.clear();
        detector = null;
    }

    @Override
    public void endDocument() throws SAXException {
        detector = builder.build();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
        xPath.add(qName);
        textBuffer = new StringBuilder();

        if (isXPath(XPATH_TO_FEATURE)) {
            builder.addFeature();
        } else if(isXPath(XPATH_TO_STAGE)) {
            builder.addStage();
        } else if(isXPath(XPATH_TO_WEAK_CLASSIFIER)) {
            builder.addWeakClassifier();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //TODO probably it can be optimised to not store uninteresting data
        textBuffer.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(isXPath(XPATH_TO_FEATURE_PART)){
            String text = textBuffer.toString().strip();
            var tokens = text.split("\\s");
            builder.addFeaturePart(
                    parseInt(tokens[0]),
                    parseInt(tokens[1]),
                    parseInt(tokens[2]),
                    parseInt(tokens[3]),
                    parseDouble(tokens[4])
            );
        } else if(isXPath(XPATH_TO_STAGE_THRESHOLD)) {
            builder.setStageThreshold(parseDouble(textBuffer.toString()));
        } else if(isXPath(XPATH_TO_WINDOW_WIDTH)) {
            builder.setWindowWidth(parseInt(textBuffer.toString()));
        } else if(isXPath(XPATH_TO_WINDOW_HEIGHT)) {
            builder.setWindowHeight(parseInt(textBuffer.toString()));
        } else if(isXPath(XPATH_TO_CLASSIFIER_NODE)) {
            String text = textBuffer.toString().strip();
            var tokens = text.split("\\s");
            if (tokens.length != 4) {
                //TODO implement rees like in OpenCV
                throw new RuntimeException("Only stumps are supported");
            }
            builder.setWeakClassifierFeatureIndex(parseInt(tokens[2]));
            builder.setWeakClassifierThreshold(parseDouble(tokens[3]));
        } else if(isXPath(XPATH_TO_CLASSIFIER_LEAF)) {
            String text = textBuffer.toString().strip();
            var tokens = text.split("\\s");
            if (tokens.length != 2) {
                //TODO implement rees like in OpenCV
                throw new RuntimeException("Only stumps are supported");
            }
            builder.setWeakClassifierLeftValue(parseDouble(tokens[0]));
            builder.setWeakClassifierRightValue(parseDouble(tokens[1]));
        }

        xPath.remove(xPath.size() - 1);
    }

    private boolean isXPath(List<String> xPath) {
        return this.xPath.equals(xPath);
    }

    public Detector getDetector() {
        return detector;
    }
}