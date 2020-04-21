package eggplant.backend.repository;

import eggplant.backend.model.PredictionInformation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PredictionInformationRepository extends MongoRepository<PredictionInformation, String> {

    List<PredictionInformation> getByClassifierId(String classifierId);

    List<PredictionInformation> getByZucchiniId(String zucchiniId);

    List<PredictionInformation> getByScenarioId(String scenarioId);

}
