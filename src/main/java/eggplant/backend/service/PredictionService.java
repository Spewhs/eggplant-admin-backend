package eggplant.backend.service;

import eggplant.backend.model.PredictionInformation;

import java.util.List;

public interface PredictionService {

    void submitPrediction(String scenarioId, String classifierId);

    void submitPrediction(String scenarioId);

    List<PredictionInformation> getPrediction(Integer page, Integer pageSize);

    List<PredictionInformation> getPredictionByClassifier(String classifierId, Integer page, Integer pageSize);

    List<PredictionInformation> getPredictionByScenarioId(String scenarioId, Integer page, Integer pageSize);

    List<PredictionInformation> getPredictionByZucchiniId(String zucchiniId, Integer page, Integer pageSize);

    boolean isWellPredict(String scenarioId, String classifierId);

}
