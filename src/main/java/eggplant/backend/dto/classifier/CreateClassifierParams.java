package eggplant.backend.dto.classifier;

import java.util.List;
import java.util.Optional;

public class CreateClassifierParams {

    private Optional<String> version;

    private Optional<Float> trainingAccuracy;

    private Optional<List<String>> interestingWords;

    public CreateClassifierParams(
            Optional<String> version,
            Optional<Float> trainingAccuracy,
            Optional<List<String>> interestingWords
    ) {
        this.version = version;
        this.trainingAccuracy = trainingAccuracy;
        this.interestingWords = interestingWords;
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
}
