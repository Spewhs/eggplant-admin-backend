package eggplant.backend.dto.classifier;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public class UpdateClassifierParams {

    private String id;

    private Optional<String> version;

    private Optional<Float> trainingAccuracy;

    private Optional<List<String>> interestingWords;

    private Optional<Integer> goodPrediction;

    private Optional<Integer> totalLabeledPrediction;

    private Optional<Integer> totalPrediction;

    public UpdateClassifierParams() {
    }

    public UpdateClassifierParams(
            @NotNull String id,
            Optional<String> version,
            Optional<Float> trainingAccuracy,
            Optional<List<String>> interestingWords,
            Optional<Integer> goodPrediction,
            Optional<Integer> totalLabeledPrediction,
            Optional<Integer> totalPrediction
    ) {
        this.id = id;
        this.version = version;
        this.trainingAccuracy = trainingAccuracy;
        this.interestingWords = interestingWords;
        this.goodPrediction = goodPrediction;
        this.totalLabeledPrediction = totalLabeledPrediction;
        this.totalPrediction = totalPrediction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Optional<String> getVersion() {
        return version;
    }

    public void setVersion(Optional<String> version) {
        this.version = version;
    }

    public Optional<Float> getTrainingAccuracy() {
        return trainingAccuracy;
    }

    public void setTrainingAccuracy(Optional<Float> trainingAccuracy) {
        this.trainingAccuracy = trainingAccuracy;
    }
    public Optional<List<String>> getInterestingWords() {
        return interestingWords;
    }

    public void setInterestingWords(Optional<List<String>> interestingWords) {
        this.interestingWords = interestingWords;
    }

    public Optional<Integer> getGoodPrediction() {
        return goodPrediction;
    }

    public void setGoodPrediction(Optional<Integer> goodPrediction) {
        this.goodPrediction = goodPrediction;
    }

    public Optional<Integer> getTotalPrediction() {
        return totalPrediction;
    }

    public void setTotalPrediction(Optional<Integer> totalPrediction) {
        this.totalPrediction = totalPrediction;
    }

    public Optional<Integer> getTotalLabeledPrediction() {
        return totalLabeledPrediction;
    }

    public void setTotalLabeledPrediction(Optional<Integer> totalLabeledPrediction) {
        this.totalLabeledPrediction = totalLabeledPrediction;
    }
}
