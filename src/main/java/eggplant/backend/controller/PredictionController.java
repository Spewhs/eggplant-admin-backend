package eggplant.backend.controller;

import eggplant.backend.model.PredictionInformation;
import eggplant.backend.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<PredictionInformation> getPrediction(
            @RequestParam(value = "page", defaultValue = "0") Integer page
    ) {
        return predictionService.getPrediction(page, pageSize);
    }

    @GetMapping(value = basePath + "/zucchini/{zucchiniId}")
    public List<PredictionInformation> getByZucchiniId(
            @PathVariable String zucchiniId,
            @RequestParam(value = "page", defaultValue = "0") Integer page
    ) {
        return predictionService.getPredictionByZucchiniId(zucchiniId, page, pageSize);
    }

    @GetMapping(value = basePath + "/scenario/{scenarioId}")
    public List<PredictionInformation> getByScenarioId(
            @PathVariable String scenarioId,
            @RequestParam(value = "page", defaultValue = "0") Integer page
    ) {
        return predictionService.getPredictionByScenarioId(scenarioId, page, pageSize);
    }

    @GetMapping(value = basePath + "/classifier/{classifierId}")
    public List<PredictionInformation> getByClassifierId(
            @PathVariable String classifierId,
            @RequestParam(value = "page", defaultValue = "0") Integer page
    ) {
        return predictionService.getPredictionByClassifier(classifierId, page, pageSize);
    }
}
