package eggplant.backend.controller;

import eggplant.backend.dto.ObjectPage;
import eggplant.backend.model.PredictionInformation;
import eggplant.backend.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
public class PredictionController {

    static final String basePath = "prediction";

    static final private int pageSize = 50;

    @Autowired
    private PredictionService predictionService;

    @PostMapping(value = basePath + "/{scenarioId}")
    public void submitPrediction(
            @PathVariable String scenarioId,
            @RequestParam(value = "classifierId", defaultValue = "") String classifierId
    ) {
        if (classifierId.equals("")) {
            predictionService.submitPrediction(scenarioId);
        } else {
            predictionService.submitPrediction(scenarioId, classifierId);
        }
    }

    @GetMapping(value = basePath)
    public ObjectPage<PredictionInformation> getPrediction(
            @RequestParam(value = "page", defaultValue = "0") Integer page
    ) {
        Page<PredictionInformation> pagePrediction = predictionService.getPrediction(
                page,
                pageSize
        );
        return new ObjectPage<>(
                pagePrediction.getContent(),
                (int) pagePrediction.getTotalElements()
        );
    }

    @GetMapping(value = basePath + "/zucchini/{zucchiniId}")
    public ObjectPage<PredictionInformation> getByZucchiniId(
            @PathVariable String zucchiniId,
            @RequestParam(value = "page", defaultValue = "0") Integer page
    ) {
        Page<PredictionInformation> pagePrediction = predictionService.getPredictionByZucchiniId(
                zucchiniId,
                page,
                pageSize
        );
        return new ObjectPage<>(
                pagePrediction.getContent(),
                (int) pagePrediction.getTotalElements()
        );
    }

    @GetMapping(value = basePath + "/scenario/{scenarioId}")
    public ObjectPage<PredictionInformation> getByScenarioId(
            @PathVariable String scenarioId,
            @RequestParam(value = "page", defaultValue = "0") Integer page
    ) {
        Page<PredictionInformation> pagePrediction = predictionService.getPredictionByScenarioId(
                scenarioId,
                page,
                pageSize
        );
        return new ObjectPage<>(
                pagePrediction.getContent(),
                (int) pagePrediction.getTotalElements()
        );
    }

    @GetMapping(value = basePath + "/classifier/{classifierId}")
    public ObjectPage<PredictionInformation> getByClassifierId(
            @PathVariable String classifierId,
            @RequestParam(value = "page", defaultValue = "0") Integer page
    ) {
        Page<PredictionInformation> pagePrediction =  predictionService.getPredictionByClassifier(
                classifierId,
                page,
                pageSize
        );
        return new ObjectPage<>(
                pagePrediction.getContent(),
                (int) pagePrediction.getTotalElements()
        );
    }
}
