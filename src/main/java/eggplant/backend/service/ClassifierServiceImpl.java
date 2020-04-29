package eggplant.backend.service;

import eggplant.backend.dto.classifier.UpdateClassifierParams;
import eggplant.backend.exception.NoDocument;
import eggplant.backend.rabbitmq.producer.EggplantTrainingProducer;
import eggplant.backend.repository.ClassifierRepository;
import eggplant.backend.dto.classifier.CreateClassifierParams;
import eggplant.backend.exception.CantActiveClassifier;
import eggplant.backend.model.Classifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@Component
public class ClassifierServiceImpl implements ClassifierService {

    @Autowired
    private ClassifierRepository classifierRepository;

    @Autowired
    private EggplantTrainingProducer eggplantTrainingProducer;

    @Override
    public Page<Classifier> getClassifierPage(Integer page, Integer pageSize) {
        return classifierRepository.findAll(
                PageRequest.of(page, page + pageSize, Sort.by("createdAt").ascending())
        );
    }

    @Override
    public Classifier getByVersionOrActive(String version) {
        Classifier responseClassifier = classifierRepository.findByVersion(version);
        if (responseClassifier == null) {
            responseClassifier = classifierRepository.getClassifierByActiveClassifierIsTrue();
        }
        return responseClassifier;
    }

    @Override
    public Classifier getById(String id) {
        Classifier classifier = classifierRepository.findById(id)
                .orElseThrow(NoDocument::new);
        return classifier;
    }

    @Override
    public Classifier createNewClassifier(CreateClassifierParams params) {
        String  version = "";
        if (params.getVersion().isEmpty()) {
            version = "" + (classifierRepository.findAll().size() + 1);
        }
        Classifier classifier = new Classifier(
                params.getVersion().orElse(version),
                params.getTrainingAccuracy().orElse((float) -1),
                ZonedDateTime.now(ZoneOffset.UTC),
                params.getInterestingWords().orElse(new ArrayList<>()),
                0,
                0,
                false
        );
        classifierRepository.insert(classifier);
        return classifier;
    }

    @Override
    public Classifier updateClassifier(UpdateClassifierParams params) {
        Classifier classifierToUpdate = classifierRepository.findById(params.getId())
                .orElseThrow(NoDocument::new);

        params.getGoodPrediction()
                .ifPresent(classifierToUpdate::setGoodPrediction);

        params.getInterestingWords()
                .ifPresent(classifierToUpdate::setInterestingWords);

        params.getTotalPrediction()
                .ifPresent(classifierToUpdate::setTotalPrediction);

        params.getVersion()
                .ifPresent(classifierToUpdate::setVersion);

        classifierRepository.save(classifierToUpdate);
        return  classifierToUpdate;
    }

    @Override
    public Classifier changeActiveClassifier(String id) throws CantActiveClassifier {
        Classifier newActiveClassifier = classifierRepository.findById(id)
                .orElseThrow(CantActiveClassifier::new);

        Classifier oldActiveClassifier = classifierRepository.getClassifierByActiveClassifierIsTrue();

        if (oldActiveClassifier != null) {
            oldActiveClassifier.setActiveClassifier(false);
            classifierRepository.save(oldActiveClassifier);
        }

        newActiveClassifier.setActiveClassifier(true);
        classifierRepository.save(newActiveClassifier);
        return newActiveClassifier;
    }

    @Override
    public Classifier getActiveClassifier() {
        return classifierRepository.getClassifierByActiveClassifierIsTrue();
    }

    @Override
    public void submitNewTraining() {
        eggplantTrainingProducer.submitNewTraining();
    }

    @Override
    public void deleteById(String id) {
        classifierRepository.deleteById(id);
    }
}
