package eggplant.backend.dto.classifier;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateClassifierRequest {

    private String version;

    private Float trainingAccuracy;

    private List<String> interestingWords;

    public CreateClassifierRequest(
            String version,
            Float trainingAccuracy,
            List<String> interestingWords
    ) {
        this.version = version;
        this.trainingAccuracy = trainingAccuracy;
        this.interestingWords = interestingWords;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Float getTrainingAccuracy() {
        return trainingAccuracy;
    }

    public void setTrainingAccuracy(Float trainingAccuracy) {
        this.trainingAccuracy = trainingAccuracy;
    }

    public List<String> getInterestingWords() {
        return interestingWords;
    }

    public void setInterestingWords(List<String> interestingWords) {
        this.interestingWords = interestingWords;
    }
}
