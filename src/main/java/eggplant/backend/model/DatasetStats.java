package eggplant.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.HashMap;


@Document("datasetStats")
public class DatasetStats {

    @Id
    private String id;

    private Integer datasetSize;

    private HashMap<String, Integer> errorsDistribution;

    private HashMap<String, Integer> correctionActionDistribution;

    private HashMap<String, Integer> failStepKeyWordDistribution;

    // trainingLabel: [ correctionAction -> nb apparition ]
    private HashMap<String, HashMap<String, Integer>> correctionErrorAggregation;

    private ZonedDateTime updatedAt;

    public DatasetStats() { }

    public DatasetStats(
            String id,
            Integer datasetSize,
            HashMap<String, Integer> errorsDistribution,
            HashMap<String, Integer> correctionActionDistribution,
            HashMap<String, Integer> failStepKeyWordDistribution,
            HashMap<String, HashMap<String, Integer>> correctionErrorAggregation,
            ZonedDateTime updatedAt
    ) {
        this.id = id;
        this.datasetSize = datasetSize;
        this.errorsDistribution = errorsDistribution;
        this.correctionActionDistribution = correctionActionDistribution;
        this.failStepKeyWordDistribution = failStepKeyWordDistribution;
        this.correctionErrorAggregation = correctionErrorAggregation;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public Integer getDatasetSize() {
        return datasetSize;
    }

    public void setDatasetSize(Integer datasetSize) {
        this.datasetSize = datasetSize;
    }

    public HashMap<String, Integer> getErrorsDistribution() {
        return errorsDistribution;
    }

    public void setErrorsDistribution(HashMap<String, Integer> errorsDistribution) {
        this.errorsDistribution = errorsDistribution;
    }

    public HashMap<String, Integer> getCorrectionActionDistribution() {
        return correctionActionDistribution;
    }

    public void setCorrectionActionDistribution(HashMap<String, Integer> correctionActionDistribution) {
        this.correctionActionDistribution = correctionActionDistribution;
    }

    public HashMap<String, Integer> getFailStepKeyWordDistribution() {
        return failStepKeyWordDistribution;
    }

    public void setFailStepKeyWordDistribution(HashMap<String, Integer> failStepKeyWordDistribution) {
        this.failStepKeyWordDistribution = failStepKeyWordDistribution;
    }

    public HashMap<String, HashMap<String, Integer>> getCorrectionErrorAggregation() {
        return correctionErrorAggregation;
    }

    public void setCorrectionErrorAggregation(HashMap<String, HashMap<String, Integer>> correctionErrorAggregation) {
        this.correctionErrorAggregation = correctionErrorAggregation;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
