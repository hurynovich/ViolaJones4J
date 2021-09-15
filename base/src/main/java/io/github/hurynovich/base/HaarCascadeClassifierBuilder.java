package io.github.hurynovich.base;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

final class HaarCascadeClassifierBuilder {
    private final List<StageData> stages = new ArrayList<>();
    private final List<FeatureData> features = new ArrayList<>();
    private FeatureData currentFeature;
    private StageData currentStage;
    private WeakClassifierData currentWeakClassifier;

    @Setter
    private int windowWidth;
    @Setter
    private int windowHeight;

    public HaarCascadeClassifierBuilder(){
        clear();
    }

    public void clear(){
        stages.clear();
        features.clear();

        currentFeature = null;
        currentStage = null;
        currentWeakClassifier = null;
    }

    public void addStage(){
        currentStage = new StageData();
        stages.add(currentStage);
    }

    public void setStageThreshold(double threshold){
        currentStage.threshold = threshold;
    }

    public void addWeakClassifier() {
        currentWeakClassifier = new WeakClassifierData();
        currentStage.weakClassifiers.add(currentWeakClassifier);
    }

    public void setWeakClassifierThreshold(double threshold){
        currentWeakClassifier.threshold = threshold;
    }

    public void setWeakClassifierFeatureIndex(int index){
        currentWeakClassifier.featureIndex = index;
    }

    public void setWeakClassifierLeftValue(double value){
        currentWeakClassifier.leftValue = value;
    }

    public void setWeakClassifierRightValue(double value){
        currentWeakClassifier.rightValue = value;
    }

    public void addFeature(){
        currentFeature = new FeatureData();
        features.add(currentFeature);
    }

    public void addFeaturePart(int x1, int y1, int x2, int y2, double factor) {
        HaarFeature.Part part = new HaarFeature.Part(new Rect(x1, y1, x2, y2), factor);
        currentFeature.parts.add(part);
    }

    public Detector build(){
        List<HaarFeature> features = new ArrayList<>();
        for (var f : this.features) {
            features.add(new HaarFeature(f.parts));
        }

        List<StrongHaarClassifier> stages = new ArrayList<>();
        for (var stageData : this.stages) {
            stages.add(buildStage(stageData, features));
        }
        var cascade = new CascadeClassifier(stages, new Int2D(windowWidth, windowHeight));
        return new CascadeClassifierDetector(cascade);
    }

    private StrongHaarClassifier buildStage(StageData stageData, List<HaarFeature> features) {
        List<WeakHaarClassifier> weakClassifiers = new ArrayList<>();
        for (var w : stageData.weakClassifiers) {
            weakClassifiers.add(new WeakHaarClassifier(w.threshold, w.leftValue, w.rightValue, features.get(w.featureIndex)));
        }
        return new StrongHaarClassifier(weakClassifiers, stageData.threshold);
    }

    private static class StageData {
        double threshold;
        final List<WeakClassifierData> weakClassifiers = new ArrayList<>();
    }

    /**
     * Intermediate class which stores all the data necessary to build
     * {@link WeakHaarClassifier} instance. It is necessary because of
     * features date is stored at the end of XML file and cannot be loaded at once.
     */
    private static class WeakClassifierData {
        double threshold;
        double leftValue;
        double rightValue;
        int featureIndex;
    }

    /**
     * Intermediate class which stores all the data necessary to build
     * {@link WeakHaarClassifier} instance. It is necessary because of
     * features date is stored at the end of XML file and cannot be loaded at once.
     */
    private static class FeatureData {
        List<HaarFeature.Part> parts = new ArrayList<>();
    }
}
