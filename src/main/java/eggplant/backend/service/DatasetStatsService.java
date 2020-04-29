package eggplant.backend.service;

import eggplant.backend.dto.stats.NewEntryInDataset;
import eggplant.backend.model.DatasetStats;

public interface DatasetStatsService {

    void addNewEntry(NewEntryInDataset newEntryInDataset);

    DatasetStats getDatasetStatistics();

}
