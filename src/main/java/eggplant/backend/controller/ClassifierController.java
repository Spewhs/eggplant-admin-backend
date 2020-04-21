package eggplant.backend.controller;

import eggplant.backend.dto.ObjectPage;
import eggplant.backend.dto.ResponseObjectId;
import eggplant.backend.dto.classifier.UpdateClassifierParams;
import eggplant.backend.dto.classifier.UpdateClassifierRequest;
import eggplant.backend.dto.classifier.CreateClassifierParams;
import eggplant.backend.dto.classifier.CreateClassifierRequest;
import eggplant.backend.model.Classifier;
import eggplant.backend.service.ClassifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ClassifierController {

    static final String basePath = "classifier";

    static final private int pageSize = 50;

    @Autowired
    private ClassifierService classifierService;

    @GetMapping(value = basePath)
    public ObjectPage<Classifier> getClassifier(
            @RequestParam(value = "page", defaultValue = "0") Integer page
            ) {
        Page<Classifier> classifierPage = classifierService.getClassifierPage(page, pageSize);
        return new ObjectPage<>(
                classifierPage.toList(),
                (int) classifierPage.getTotalElements()
        );
    }

    @PostMapping(value = basePath + "/train")
    public void submitTraining(){
        classifierService.submitNewTraining();
    }

    @GetMapping(value = basePath + "/version/{version}")
    public Classifier getClassifierByVersion(
            @PathVariable String version
    ) {
        /*
        * Return classifier object with specific version
        * Or the current active classifier
        * */
        return classifierService.getByVersionOrActive(version);
    }

    @GetMapping(value = basePath + "/{id}")
    @ResponseBody
    public Classifier getClassifierById(
            @PathVariable String id
    ) {
        return classifierService.getById(id);
    }

    @PostMapping(value = basePath)
    public Classifier createNewClassifier(
            @RequestBody final CreateClassifierRequest request
    ) {
        final CreateClassifierParams params = new CreateClassifierParams(
                Optional.ofNullable(request.getVersion()),
                Optional.ofNullable(request.getTrainingAccuracy()),
                Optional.ofNullable(request.getInterestingWords())
        );
        return classifierService.createNewClassifier(params);
    }

    @PatchMapping(value = basePath + "/{id}/active")
    @ResponseBody
    public ResponseObjectId changeActiveClassifier(
            @PathVariable String id
    ) {
        Classifier updatedClassifier = classifierService.changeActiveClassifier(id);
        return new ResponseObjectId(updatedClassifier.getId());
    }

    @PatchMapping(value = basePath)
    public Classifier updateClassifier(
            @RequestBody final UpdateClassifierRequest request
            ) {
        UpdateClassifierParams params = new UpdateClassifierParams(
                request.getId(),
                Optional.ofNullable(request.getVersion()),
                Optional.ofNullable(request.getTrainingAccuracy()),
                Optional.ofNullable(request.getInterestingWords()),
                Optional.ofNullable(request.getGoodPrediction()),
                Optional.ofNullable(request.getTotalPrediction())
        );
        return classifierService.updateClassifier(params);
    }

    @DeleteMapping(value = basePath + "/{id}")
    public ResponseObjectId deleteClassifierById(
            @PathVariable String id
    ) {
        classifierService.deleteById(id);
        return new ResponseObjectId(id);
    }

}
