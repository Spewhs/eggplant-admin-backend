package eggplant.backend.controller;

import eggplant.backend.dto.ObjectPage;
import eggplant.backend.dto.scenario.CreateScenarioParams;
import eggplant.backend.dto.scenario.CreateScenarioRequest;
import eggplant.backend.dto.scenario.UpdateScenarioParams;
import eggplant.backend.dto.scenario.UpdateScenarioRequest;
import eggplant.backend.model.Scenario;
import eggplant.backend.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ScenarioController {

    // Todo ajouter des informations sur tous le dataset (répartitions erreur, entrées)

    static final String basePath = "scenario";

    static final private int pageSize = 50;

    @Autowired
    private ScenarioService scenarioService;

    @GetMapping(value = basePath)
    public ObjectPage<Scenario> getAllScenario(
            @RequestParam(value = "page", defaultValue = "0") Integer page
    ) {
        Page<Scenario> scenarioPage = scenarioService.getScenarioPage(page, pageSize);
        return new ObjectPage<>(
                scenarioPage.toList(),
                (int) scenarioPage.getTotalElements()
        );
    }

    @GetMapping(value = basePath + "/{id}")
    public Scenario findById(
            @PathVariable String id
    ) {
        return scenarioService.getScenarioById(id);
    }

    @GetMapping(value = basePath + "/dataset")
    public List<Scenario> getDataset(
            @RequestParam(value = "page", defaultValue = "0") Integer page
    ) {
        Slice<Scenario> scenarios = scenarioService.getDataset(page, pageSize);
        return scenarios.getContent();
    }

    @PatchMapping(value = basePath + "/dataset/{id}")
    @ResponseBody
    public Scenario changeScenarioDatasetStatus(
            @PathVariable String id
    ) {
        return scenarioService.changeScenarioStatus(id);
    }

    @PatchMapping(value = basePath)
    @ResponseBody
    public Scenario updateScenario(
            @RequestBody UpdateScenarioRequest request
    ) {
        UpdateScenarioParams params = new UpdateScenarioParams(
                request.getId(),
                Optional.of(request.getTrainingLabel()),
                Optional.of(request.getCorrectionAction()),
                Optional.of(request.getUsedInDataset())
        );
        return scenarioService.updateScenarioWithId(params);
    }

    @PostMapping(value = basePath)
    public Scenario createScenario(
            @RequestBody CreateScenarioRequest request
    ) {
        CreateScenarioParams params = new CreateScenarioParams(
                request.getZucchiniId(),
                request.getTrace(),
                Optional.of(request.getTrainingLabel()),
                Optional.of(request.getCorrectionAction()),
                request.getScenarioKey(),
                request.getFailStepKeyWord()
        );
        return scenarioService.createScenario(params);
    }
}
