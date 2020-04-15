package eggplant.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.Objects;

@Document("dataset")
public class Scenario {

    @Id
    private String id;

    private String zucchiniId;

    private String trace;

    private String trainingLabel;

    private String correctionAction;

    private String scenarioKey;

    private String failStepKeyWord;

    private ZonedDateTime createdAt;

    private boolean usedInDataset;

    public Scenario() { }

    public Scenario(
            String zucchiniId,
            String trace,
            String trainingLabel,
            String correctionAction,
            String scenarioKey,
            String failStepKeyWord,
            ZonedDateTime createdAt,
            boolean usedInDataset
    ) {
        this.zucchiniId = zucchiniId;
        this.trace = trace;
        this.trainingLabel = trainingLabel;
        this.correctionAction = correctionAction;
        this.scenarioKey = scenarioKey;
        this.failStepKeyWord = failStepKeyWord;
        this.createdAt = createdAt;
        this.usedInDataset = usedInDataset;
    }

    public String getId() {
        return id;
    }

    public String getZucchiniId() {
        return zucchiniId;
    }

    public void setZucchiniId(String zucchiniId) {
        this.zucchiniId = zucchiniId;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String getTrainingLabel() {
        return trainingLabel;
    }

    public void setTrainingLabel(String trainingLabel) {
        this.trainingLabel = trainingLabel;
    }

    public String getCorrectionAction() {
        return correctionAction;
    }

    public void setCorrectionAction(String correctionAction) {
        this.correctionAction = correctionAction;
    }

    public String getScenarioKey() {
        return scenarioKey;
    }

    public void setScenarioKey(String scenarioKey) {
        this.scenarioKey = scenarioKey;
    }

    public String getFailStepKeyWord() {
        return failStepKeyWord;
    }

    public void setFailStepKeyWord(String failStepKeyWord) {
        this.failStepKeyWord = failStepKeyWord;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isUsedInDataset() {
        return usedInDataset;
    }

    public void setUsedInDataset(boolean usedInDataset) {
        if (trainingLabel == null) {
            usedInDataset = false;
            return;
        }
        this.usedInDataset = usedInDataset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scenario scenario = (Scenario) o;
        return Objects.equals(id, scenario.id) &&
                Objects.equals(zucchiniId, scenario.zucchiniId) &&
                Objects.equals(trace, scenario.trace) &&
                Objects.equals(trainingLabel, scenario.trainingLabel) &&
                Objects.equals(correctionAction, scenario.correctionAction) &&
                Objects.equals(scenarioKey, scenario.scenarioKey) &&
                Objects.equals(failStepKeyWord, scenario.failStepKeyWord) &&
                Objects.equals(createdAt, scenario.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, zucchiniId, trace, trainingLabel, correctionAction, scenarioKey, failStepKeyWord, createdAt);
    }

    @Override
    public String toString() {
        return "Scenario{" +
                "id='" + id + '\'' +
                ", zucchiniId='" + zucchiniId + '\'' +
                ", trainingLabel='" + trainingLabel + '\'' +
                ", correctionAction='" + correctionAction + '\'' +
                ", scenarioKey='" + scenarioKey + '\'' +
                ", failStepKeyWord='" + failStepKeyWord + '\'' +
                '}';
    }
}
