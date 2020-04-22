package eggplant.backend.repository;

import eggplant.backend.model.PredictionInformation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PredictionInformationRepository extends MongoRepository<PredictionInformation, String> {

    List<PredictionInformation> getByClassifierId(String classifierId, Pageable pageable);

    List<PredictionInformation> getByZucchiniId(String zucchiniId, Pageable pageable);

    List<PredictionInformation> getByScenarioId(String scenarioId, Pageable pageable);

    Optional<PredictionInformation> getByZucchiniIdAndClassifierId(String zucchiniId, String classifierId);

}
