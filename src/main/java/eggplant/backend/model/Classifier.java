package eggplant.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@Document("classifier")
public class Classifier {

    @Id
    private String id;

    private String version;

    private float trainingAccuracy;

    private ZonedDateTime createdAd;

    private List<String> interestingWords;

    private Integer goodPrediction;

    private Integer totalPrediction;

    private boolean activeClassifier;

    public Classifier() { }

    public Classifier(
            String version,
            float trainingAccuracy,
            ZonedDateTime createdAd,
            List<String> interestingWords,
            Integer goodPrediction,
            Integer totalPrediction,
            boolean isActiveClassifier
    ) {
        this.version = version;
        this.trainingAccuracy = trainingAccuracy;
        this.createdAd = createdAd;
        this.interestingWords = interestingWords;
        this.goodPrediction = goodPrediction;
        this.totalPrediction = totalPrediction;
        this.activeClassifier = isActiveClassifier;
    }

    public String getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public float getTrainingAccuracy() {
        return trainingAccuracy;
    }

    public void setTrainingAccuracy(float trainingAccuracy) {
        this.trainingAccuracy = trainingAccuracy;
    }

    public ZonedDateTime getCreatedAd() {
        return createdAd;
    }

    public void setCreatedAd(ZonedDateTime createdAd) {
        this.createdAd = createdAd;
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

    public boolean isActiveClassifier() {
        return activeClassifier;
    }

    public void setActiveClassifier(boolean activeClassifier) {
        this.activeClassifier = activeClassifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classifier that = (Classifier) o;
        return Float.compare(that.trainingAccuracy, trainingAccuracy) == 0 &&
                activeClassifier == that.activeClassifier &&
                Objects.equals(id, that.id) &&
                Objects.equals(version, that.version) &&
                Objects.equals(createdAd, that.createdAd) &&
                Objects.equals(interestingWords, that.interestingWords) &&
                Objects.equals(goodPrediction, that.goodPrediction) &&
                Objects.equals(totalPrediction, that.totalPrediction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, trainingAccuracy, createdAd, interestingWords, goodPrediction, totalPrediction, activeClassifier);
    }

    @Override
    public String toString() {
        return "Classifier{" +
                "id='" + id + '\'' +
                ", version='" + version + '\'' +
                ", trainingAccuracy=" + trainingAccuracy +
                ", createdAd=" + createdAd +
                ", goodPrediction=" + goodPrediction +
                ", totalPrediction=" + totalPrediction +
                '}';
    }
}
