package eggplant.backend.dto.classifier;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties
public class CreateRabbitMQClassifierParams {

    @JsonProperty("version")
    private String version;

    @JsonProperty("trainingAccuracy")
    private Float trainingAccuracy;

    @JsonProperty("interestingWords")
    private List<String> interestingWords;

    public CreateRabbitMQClassifierParams() {
    }

    public CreateRabbitMQClassifierParams(
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
