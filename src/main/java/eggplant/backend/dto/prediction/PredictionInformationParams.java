package eggplant.backend.dto.prediction;

import eggplant.backend.model.Prediction;

import java.util.List;

public class PredictionInformationParams {

    private List<Prediction> predictions;

    private String classifierId;

    private String zucchiniId;

    private String scenarioId;

    public PredictionInformationParams(
            List<Prediction> predictions,
            String classifierId,
            String zucchiniId,
            String scenarioId
    ) {
        this.predictions = predictions;
        this.classifierId = classifierId;
        this.zucchiniId = zucchiniId;
        this.scenarioId = scenarioId;
    }

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

    public String getClassifierId() {
        return classifierId;
    }

    public void setClassifierId(String classifierId) {
        this.classifierId = classifierId;
    }

    public String getZucchiniId() {
        return zucchiniId;
    }

    public void setZucchiniId(String zucchiniId) {
        this.zucchiniId = zucchiniId;
    }

    public String getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }
}
