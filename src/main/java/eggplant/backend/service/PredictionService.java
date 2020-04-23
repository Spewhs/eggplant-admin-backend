package eggplant.backend.service;

import eggplant.backend.model.PredictionInformation;
import org.springframework.data.domain.Page;


public interface PredictionService {

    void submitPrediction(String scenarioId, String classifierId);

    void submitPrediction(String scenarioId);

    Page<PredictionInformation> getPrediction(Integer page, Integer pageSize);

    Page<PredictionInformation> getPredictionByClassifier(String classifierId, Integer page, Integer pageSize);

    Page<PredictionInformation> getPredictionByScenarioId(String scenarioId, Integer page, Integer pageSize);

    Page<PredictionInformation> getPredictionByZucchiniId(String zucchiniId, Integer page, Integer pageSize);

    boolean isWellPredict(String scenarioId, String classifierId);

}
