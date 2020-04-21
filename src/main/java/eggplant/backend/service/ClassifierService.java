package eggplant.backend.service;

import eggplant.backend.dto.classifier.CreateClassifierParams;
import eggplant.backend.dto.classifier.UpdateClassifierParams;
import eggplant.backend.exception.CantActiveClassifier;
import eggplant.backend.model.Classifier;
import org.springframework.data.domain.Page;

public interface ClassifierService {

    Page<Classifier> getClassifierPage(Integer page, Integer pageSize);

    Classifier getByVersionOrActive(String version);

    Classifier getById(String id);

    Classifier createNewClassifier(CreateClassifierParams params);

    Classifier updateClassifier(UpdateClassifierParams params);

    Classifier changeActiveClassifier(String id) throws CantActiveClassifier;

    Classifier getActiveClassifier();

    void deleteById(String id);

    // TODO Add something to manage number of prediction

}
