package eggplant.backend.service;

import eggplant.backend.dto.prediction.PredictionInformationParams;
import eggplant.backend.model.PredictionInformation;
import eggplant.backend.repository.PredictionInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PredictionInformationServiceImpl implements PredictionInformationService {

    @Autowired
    private PredictionInformationRepository predictionInformationRepository;


    @Override
    public PredictionInformation insertNewPrediction(PredictionInformationParams predictionInformationParams) {
        PredictionInformation predictionInformation = new PredictionInformation(
                predictionInformationParams.getPredictions(),
                predictionInformationParams.getClassifierId(),
                predictionInformationParams.getZucchiniId(),
                predictionInformationParams.getScenarioId()
        );
        predictionInformationRepository.insert(predictionInformation);
        return predictionInformation;
    }

}
