package eggplant.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import eggplant.backend.exception.NoDocument;
import eggplant.backend.model.PredictionInformation;
import eggplant.backend.model.Scenario;
import eggplant.backend.rabbitmq.producer.EggplantPredictionProducer;
import eggplant.backend.repository.PredictionInformationRepository;
import eggplant.backend.repository.ScenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PredictionServiceImpl implements PredictionService {

    @Autowired
    private EggplantPredictionProducer eggplantPredictionProducer;

    @Autowired
    private PredictionInformationRepository predictionInformationRepository;

    @Autowired
    private ScenarioRepository scenarioRepository;

    @Override
    public void submitPrediction(String scenarioId, String classifierId) {
        Scenario scenario = scenarioRepository.findById(scenarioId)
                .orElseThrow(NoDocument::new);

        try {
            eggplantPredictionProducer.submitPrediction(scenario, classifierId);
        } catch (JsonProcessingException e) {
            // Todo add Error handler
            e.printStackTrace();
        }

    }

    @Override
    public void submitPrediction(String scenarioId) {
        Scenario scenario = scenarioRepository.findById(scenarioId)
                .orElseThrow(NoDocument::new);

        try {
            eggplantPredictionProducer.submitPrediction(scenario);
        } catch (JsonProcessingException e) {
            // Todo add Error handler
            e.printStackTrace();
        }

    }

    @Override
    public Page<PredictionInformation> getPrediction(Integer page, Integer pageSize) {
        return predictionInformationRepository.findAll(PageRequest.of(page, page + pageSize));
    }

    @Override
    public Page<PredictionInformation> getPredictionByClassifier(String classifierId, Integer page, Integer pageSize) {
        return predictionInformationRepository.getByClassifierId(
                classifierId,
                PageRequest.of(page, page + pageSize)
        );
    }

    @Override
    public Page<PredictionInformation> getPredictionByScenarioId(String scenarioId, Integer page, Integer pageSize) {
        return predictionInformationRepository.getByScenarioId(
                scenarioId,
                PageRequest.of(page, page + pageSize)
        );
    }

    @Override
    public Page<PredictionInformation> getPredictionByZucchiniId(String zucchiniId, Integer page, Integer pageSize) {
        return predictionInformationRepository.getByZucchiniId(
                zucchiniId,
                PageRequest.of(page, page + pageSize)
        );
    }

    @Override
    public boolean isWellPredict(String zucchiniId, String classifierId) {
        PredictionInformation predictionInformation = predictionInformationRepository.getByZucchiniIdAndClassifierId(
                zucchiniId,
                classifierId
        ).orElseThrow(NoDocument::new);

        Scenario scenario = scenarioRepository.findByZucchiniId(zucchiniId)
                .orElseThrow(NoDocument::new);

        return scenario.getTrainingLabel().equals(predictionInformation.getPrediction());
    }
}
