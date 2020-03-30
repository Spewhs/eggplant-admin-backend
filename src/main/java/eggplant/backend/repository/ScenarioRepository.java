package eggplant.backend.repository;

import eggplant.backend.model.Scenario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ScenarioRepository extends MongoRepository<Scenario, String> {

    Scenario findByZucchiniId(String zucchiniId);

    List<Scenario> findByTrainingLabel(String trainingLabel, Pageable pageable);

    List<Scenario> findByCorrectionAction(String correctionAction);

    List<Scenario> findByFailStepKeyWord(String failStepKeyWord);

    List<Scenario> findByScenarioKey(String scenarioKey);

    Slice<Scenario> findByUsedInDatasetIsTrue(Pageable pageable);
}
