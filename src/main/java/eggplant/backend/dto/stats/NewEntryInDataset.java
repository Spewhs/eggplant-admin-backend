package eggplant.backend.dto.stats;

public class NewEntryInDataset {

    private String trainingLabel;

    private String correctionAction;

    private String failStepKeyword;

    public NewEntryInDataset(
            String trainingLabel,
            String correctionAction,
            String failStepKeyword
    ) {
        this.trainingLabel = trainingLabel;
        this.correctionAction = correctionAction;
        this.failStepKeyword = failStepKeyword;
    }

    public String getTrainingLabel() {
        return trainingLabel;
    }

    public String getCorrectionAction() {
        return correctionAction;
    }

    public String getFailStepKeyword() {
        return failStepKeyword;
    }
}
