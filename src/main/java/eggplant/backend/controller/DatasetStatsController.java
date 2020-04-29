package eggplant.backend.controller;

import eggplant.backend.model.DatasetStats;
import eggplant.backend.service.DatasetStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatasetStatsController {

    static final String basePath = "stats";

    @Autowired
    private DatasetStatsService datasetStatsService;

    @GetMapping(value = basePath)
    public DatasetStats getDatasetStats() {
        return datasetStatsService.getDatasetStatistics();
    }
}
