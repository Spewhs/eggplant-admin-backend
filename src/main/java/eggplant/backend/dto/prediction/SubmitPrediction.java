package eggplant.backend.dto.prediction;

public class SubmitPrediction {

    private String id;

    private String classifierId;

    private String trace;

    private String failStepKeyWord;

    private String zucchiniId;

    public SubmitPrediction(
            String id,
            String classifierId,
            String trace,
            String failStepKeyWord,
            String zucchiniId
    ) {
        this.id = id;
        this.classifierId = classifierId;
        this.trace = trace;
        this.failStepKeyWord = failStepKeyWord;
        this.zucchiniId = zucchiniId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassifierId() {
        return classifierId;
    }

    public void setClassifierId(String classifierId) {
        this.classifierId = classifierId;
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

    public String getZucchiniId() {
        return zucchiniId;
    }

    public void setZucchiniId(String zucchiniId) {
        this.zucchiniId = zucchiniId;
    }
}
