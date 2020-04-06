package eggplant.backend.dto.classifier;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateClassifierRequest {

    @NotNull
    private String id;

    private String version;

    private Float trainingAccuracy;

    private List<String> interestingWords;

    private Integer goodPrediction;

    private Integer totalPrediction;

    public UpdateClassifierRequest() {
    }

    public UpdateClassifierRequest(
            String id,
            String version,
            Float trainingAccuracy,
            List<String> interestingWords,
            Integer goodPrediction,
            Integer totalPrediction
    ) {
        this.id = id;
        this.version = version;
        this.trainingAccuracy = trainingAccuracy;
        this.interestingWords = interestingWords;
        this.goodPrediction = goodPrediction;
        this.totalPrediction = totalPrediction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getGoodPrediction() {
        return goodPrediction;
    }

    public void setGoodPrediction(Integer goodPrediction) {
        this.goodPrediction = goodPrediction;
    }

    public Integer getTotalPrediction() {
        return totalPrediction;
    }

    public void setTotalPrediction(Integer totalPrediction) {
        this.totalPrediction = totalPrediction;
    }
}
