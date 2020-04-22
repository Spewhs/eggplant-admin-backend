package eggplant.backend.service;

import eggplant.backend.dto.prediction.PredictionInformationParams;
import eggplant.backend.model.PredictionInformation;

public interface PredictionInformationService {

    PredictionInformation insertNewPrediction(PredictionInformationParams predictionInformation);

}
