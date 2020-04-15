package eggplant.backend.dto.zucchini;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZucchiniScenario {

    @JsonProperty("_id")
    private String id;

    private String scenarioKey;

    @JsonProperty("analysis")
    private Analysis analysis;

    @JsonProperty("steps")
    private List<Step> steps;

    public ZucchiniScenario() {}

    public ZucchiniScenario(
            String id,
            String scenarioKey,
            Analysis analysis,
            List<Step> steps
    ) {
        this.id = id;
        this.scenarioKey = scenarioKey;
        this.analysis = analysis;
        this.steps = steps;
    }

    public String getId() {
        return id;
    }

    public String getScenarioKey() {
        return scenarioKey;
    }

    public String getTrainingLabel() {
        if (analysis == null) {
            return null;
        }
        return analysis.getAction();
    }

    public String getCorrectionAction() {
        if (analysis == null) {
            return null;
        }
        return analysis.getResult();
    }

    public String getFailStepKeyWord() {
        for (Step step : steps) {
            if (step.getErrorMessage() == null) {
                return step.getKeyword();
            }
        }
        return "";
    }

    public String getTrace() {
        for (Step step : steps) {
            if (step.getErrorMessage() != null) {
                return step.getErrorMessage();
            }
        }
        // TODO Raise exception
        return "";
    }
}
