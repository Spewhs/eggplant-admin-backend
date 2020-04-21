package eggplant.backend.service;

import eggplant.backend.dto.prediction.PredictionInformationParams;
import eggplant.backend.model.PredictionInformation;

import java.util.List;

public interface PredictionInformationService {

    PredictionInformation insertNewPrediction(PredictionInformationParams predictionInformation);

    List<PredictionInformation> getPredictionsForAGivenClassifier(String classifierId);

    List<PredictionInformation> getPredictionByZucchiniId(String zucchiniId);

    List<PredictionInformation> getPredictionByScenarioId(String scenarioId);

    PredictionInformation getPredictionById(String predictionId);

}
