package eggplant.backend.dto.scenario;

public class UpdateScenarioRequest {

    private String id;

    private String trainingLabel;

    private String correctionAction;

    private Boolean usedInDataset;

    public UpdateScenarioRequest() {
    }

    public UpdateScenarioRequest(
            String id,
            String trainingLabel,
            String correctionAction,
            Boolean usedInDataset
    ) {
        this.id = id;
        this.trainingLabel = trainingLabel;
        this.correctionAction = correctionAction;
        this.usedInDataset = usedInDataset;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getUsedInDataset() {
        return usedInDataset;
    }

    public void setUsedInDataset(Boolean usedInDataset) {
        this.usedInDataset = usedInDataset;
    }
}
