package eggplant.backend.dto.prediction;

import com.fasterxml.jackson.annotation.JsonProperty;
import eggplant.backend.model.Prediction;

import java.util.List;

public class PredictionInformationParams {

    @JsonProperty("prediction")
    private List<Prediction> predictions;

    @JsonProperty("classifierId")
    private String classifierId;

    @JsonProperty("zucchiniId")
    private String zucchiniId;

    @JsonProperty("scenarioId")
    private String scenarioId;

    @JsonProperty("scenarioKey")
    private String scenarioKey;

    @JsonProperty("testRunId")
    private String testRunId;



    public PredictionInformationParams() {
    }

    public PredictionInformationParams(
            List<Prediction> predictions,
            String classifierId,
            String zucchiniId,
            String scenarioId,
            String scenarioKey,
            String testRunId
    ) {
        this.predictions = predictions;
        this.classifierId = classifierId;
        this.zucchiniId = zucchiniId;
        this.scenarioId = scenarioId;
        this.scenarioKey = scenarioKey;
        this.testRunId = testRunId;
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

    public String getScenarioKey() {
        return scenarioKey;
    }

    public void setScenarioKey(String scenarioKey) {
        this.scenarioKey = scenarioKey;
    }

    public String getTestRunId() {
        return testRunId;
    }

    public void setTestRunId(String testRunId) {
        this.testRunId = testRunId;
    }
}
