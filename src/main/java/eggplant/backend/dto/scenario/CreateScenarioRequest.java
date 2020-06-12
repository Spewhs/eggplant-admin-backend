package eggplant.backend.dto.scenario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateScenarioRequest {

    private String zucchiniId;

    private String testRunId;

    private String trace;

    private String trainingLabel;

    private String correctionAction;

    private String scenarioKey;

    private String failStepKeyWord;

    public CreateScenarioRequest() {
    }

    public CreateScenarioRequest(
            String zucchiniId,
            String testRunId,
            String trace,
            String trainingLabel,
            String correctionAction,
            String scenarioKey,
            String failStepKeyWord
    ) {
        this.zucchiniId = zucchiniId;
        this.testRunId = testRunId;
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

    public String getTestRunId() {
        return testRunId;
    }

    public void setTestRunId(String testRunId) {
        this.testRunId = testRunId;
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
