package eggplant.backend.service;

import eggplant.backend.dto.classifier.UpdateClassifierParams;
import eggplant.backend.dto.prediction.PredictionInformationParams;
import eggplant.backend.model.Classifier;
import eggplant.backend.model.PredictionInformation;
import eggplant.backend.model.Scenario;
import eggplant.backend.repository.PredictionInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class PredictionInformationServiceImpl implements PredictionInformationService {

    @Autowired
    private PredictionInformationRepository predictionInformationRepository;

    @Autowired
    private ClassifierService classifierService;

    @Autowired
    private ScenarioService scenarioService;


    @Override
    public PredictionInformation insertNewPrediction(PredictionInformationParams predictionInformationParams) {
        PredictionInformation predictionInformation = new PredictionInformation(
                predictionInformationParams.getPredictions(),
                predictionInformationParams.getClassifierId(),
                predictionInformationParams.getZucchiniId(),
                predictionInformationParams.getScenarioId()
        );
        predictionInformationRepository.insert(predictionInformation);

        updateClassifierStatistics(predictionInformation);

        return predictionInformation;
    }

    private void updateClassifierStatistics(PredictionInformation predictionInformation) {
        Scenario scenario = scenarioService.getScenarioById(predictionInformation.getScenarioId());

        Classifier classifier = classifierService.getById(predictionInformation.getClassifierId());

        if (classifier == null || scenario == null) {
            return ;
        }

        Integer totalLabeledPrediction = classifier.getTotalLabeledPrediction();
        Integer goodPrediction = classifier.getGoodPrediction();
        Integer totalPrediction = classifier.getTotalPrediction();

        if (!scenario.getTrainingLabel().equals("")) {
            totalLabeledPrediction++;
            if (scenario.getTrainingLabel().equals(predictionInformation.getPrediction())) {
                goodPrediction++;
            }
        }
        totalPrediction++;
        String pred = predictionInformation.getPrediction();
        String label = scenario.getTrainingLabel();
        UpdateClassifierParams params = new UpdateClassifierParams(
                classifier.getId(),
                Optional.ofNullable(classifier.getVersion()),
                Optional.ofNullable(classifier.getTrainingAccuracy()),
                Optional.ofNullable(classifier.getInterestingWords()),
                Optional.ofNullable(goodPrediction),
                Optional.ofNullable(totalLabeledPrediction),
                Optional.ofNullable(totalPrediction)
        );

        classifierService.updateClassifier(params);
    }

}
