package eggplant.backend.repository;

import eggplant.backend.model.EggplantConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigRepository extends MongoRepository<EggplantConfig, String> {
}
