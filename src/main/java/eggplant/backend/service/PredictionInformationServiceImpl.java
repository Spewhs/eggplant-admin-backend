package eggplant.backend.service;

import eggplant.backend.dto.prediction.PredictionInformationParams;
import eggplant.backend.exception.NoDocument;
import eggplant.backend.model.PredictionInformation;
import eggplant.backend.repository.PredictionInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public List<PredictionInformation> getPredictionsForAGivenClassifier(String classifierId) {
        return predictionInformationRepository.getByClassifierId(classifierId);
    }

    @Override
    public List<PredictionInformation> getPredictionByZucchiniId(String zucchiniId) {
        return predictionInformationRepository.getByZucchiniId(zucchiniId);
    }

    @Override
    public List<PredictionInformation> getPredictionByScenarioId(String scenarioId) {
        return predictionInformationRepository.getByScenarioId(scenarioId);
    }

    @Override
    public PredictionInformation getPredictionById(String predictionId) {
        return predictionInformationRepository.findById(predictionId)
                .orElseThrow(NoDocument::new);
    }
}
