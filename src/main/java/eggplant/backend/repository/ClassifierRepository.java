package eggplant.backend.repository;

import eggplant.backend.model.Classifier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ClassifierRepository extends MongoRepository<Classifier, String> {

    Classifier findByVersion(String version);

    List<Classifier> findByTrainingAccuracyGreaterThanEqual(float trainingAccuracy);

    Classifier getClassifierByActiveClassifierIsTrue();

}
