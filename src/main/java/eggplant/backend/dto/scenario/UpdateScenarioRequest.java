package eggplant.backend.dto.scenario;

public class UpdateScenarioRequest {

    private String id;

    private String trainingLabel;

    private String correctionAction;

    public UpdateScenarioRequest() {
    }

    public UpdateScenarioRequest(
            String id,
            String trainingLabel,
            String correctionAction
    ) {
        this.id = id;
        this.trainingLabel = trainingLabel;
        this.correctionAction = correctionAction;
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
}
