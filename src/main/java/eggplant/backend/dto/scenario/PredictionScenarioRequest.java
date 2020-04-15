package eggplant.backend.dto.scenario;

public class PredictionScenarioRequest {

    private String id;

    private String zucchiniId;

    private String trace;

    private String failStepKeyWord;

    public PredictionScenarioRequest(
            String id,
            String zucchiniId,
            String trace,
            String failStepKeyWord
    ) {
        this.id = id;
        this.zucchiniId = zucchiniId;
        this.trace = trace;
        this.failStepKeyWord = failStepKeyWord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFailStepKeyWord() {
        return failStepKeyWord;
    }

    public void setFailStepKeyWord(String failStepKeyWord) {
        this.failStepKeyWord = failStepKeyWord;
    }
}
