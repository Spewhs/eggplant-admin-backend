package eggplant.backend.repository;

import eggplant.backend.model.PredictionInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PredictionInformationRepository extends MongoRepository<PredictionInformation, String> {

    Page<PredictionInformation> getByClassifierId(String classifierId, Pageable pageable);

    Page<PredictionInformation> getByZucchiniId(String zucchiniId, Pageable pageable);

    Page<PredictionInformation> getByScenarioId(String scenarioId, Pageable pageable);

    Optional<PredictionInformation> getByZucchiniIdAndClassifierId(String zucchiniId, String classifierId);

}
