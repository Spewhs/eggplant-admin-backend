package eggplant.backend.dto.scenario;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class UpdateScenarioParams {

    @NotNull
    private String id;

    private Optional<String> trainingLabel;

    private Optional<String> correctionAction;

    private Optional<Boolean> usedInDataset;

    public UpdateScenarioParams(
            @NotNull String id,
            Optional<String> trainingLabel,
            Optional<String> correctionAction,
            Optional<Boolean> usedInDataset
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

    public Optional<Boolean> getUsedInDataset() {
        return usedInDataset;
    }

    public void setUsedInDataset(Optional<Boolean> usedInDataset) {
        this.usedInDataset = usedInDataset;
    }
}
