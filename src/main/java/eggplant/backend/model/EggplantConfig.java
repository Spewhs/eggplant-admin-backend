package eggplant.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document("config")
public class EggplantConfig {

    @Id
    private String id;

    private List<String> trainingLabels;

    private List<String> correctionActions;

    private List<String> failSteps;

    public EggplantConfig() {
    }

    public EggplantConfig(
            String id,
            List<String> trainingLabels,
            List<String> correctionActions,
            List<String> failSteps
    ) {
        this.id = id;
        this.trainingLabels = trainingLabels;
        this.correctionActions = correctionActions;
        this.failSteps = failSteps;
    }

    public String getId() {
        return id;
    }

    public List<String> getTrainingLabels() {
        return trainingLabels;
    }

    public void setTrainingLabels(List<String> trainingLabels) {
        this.trainingLabels = trainingLabels;
    }

    public List<String> getCorrectionActions() {
        return correctionActions;
    }

    public void setCorrectionActions(List<String> correctionActions) {
        this.correctionActions = correctionActions;
    }

    public List<String> getFailSteps() {
        return failSteps;
    }

    public void setFailSteps(List<String> failSteps) {
        this.failSteps = failSteps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EggplantConfig eggplantConfig = (EggplantConfig) o;
        return Objects.equals(id, eggplantConfig.id) &&
                Objects.equals(trainingLabels, eggplantConfig.trainingLabels) &&
                Objects.equals(correctionActions, eggplantConfig.correctionActions) &&
                Objects.equals(failSteps, eggplantConfig.failSteps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trainingLabels, correctionActions, failSteps);
    }

    @Override
    public String toString() {
        return "Config{" +
                "id='" + id + '\'' +
                ", trainingLabels=" + trainingLabels +
                ", correctionActions=" + correctionActions +
                ", failSteps=" + failSteps +
                '}';
    }
}
