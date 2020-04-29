package eggplant.backend.repository;

import eggplant.backend.model.DatasetStats;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatasetStatsRepository extends MongoRepository<DatasetStats, String> {

}
