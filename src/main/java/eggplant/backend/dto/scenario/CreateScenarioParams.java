package eggplant.backend.dto.scenario;

import java.util.Optional;

public class CreateScenarioParams {

    private String zucchiniId;

    private String trace;

    private Optional<String> trainingLabel;

    private Optional<String> correctionAction;

    private String scenarioKey;

    private String failStepKeyWord;

    private String testRunId;

    public CreateScenarioParams(
            String zucchiniId,
            String testRunId,
            String trace,
            Optional<String> trainingLabel,
            Optional<String> correctionAction,
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

    public Optional<String> getTrainingLabel() {
        return trainingLabel;
    }

    public void setTrainingLabel(Optional<String> trainingLabel) {
        this.trainingLabel = trainingLabel;
    }

    public Optional<String> getCorrectionAction() {
        return correctionAction;
    }

    public void setCorrectionAction(Optional<String> correctionAction) {
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
