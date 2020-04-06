package eggplant.backend.dto.scenario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateScenarioRequest {

    @NotNull
    private String zucchiniId;

    @NotNull
    private String trace;

    @NotNull
    private String trainingLabel;

    @NotNull
    private String correctionAction;

    @NotNull
    private String scenarioKey;

    @NotNull
    private String failStepKeyWord;

    public CreateScenarioRequest() {
    }

    public CreateScenarioRequest(
            String zucchiniId,
            String trace,
            String trainingLabel,
            String correctionAction,
            String scenarioKey,
            String failStepKeyWord
    ) {
        this.zucchiniId = zucchiniId;
        this.trace = trace;
        this.trainingLabel = trainingLabel;
        this.correctionAction = correctionAction;
        this.scenarioKey = scenarioKey;
        this.failStepKeyWord = failStepKeyWord;
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
}
