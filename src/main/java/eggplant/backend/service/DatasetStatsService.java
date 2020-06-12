package eggplant.backend.service;

import eggplant.backend.dto.stats.EntryInDatasetStats;
import eggplant.backend.model.DatasetStats;

public interface DatasetStatsService {

    void addNewEntry(EntryInDatasetStats entryInDatasetStats);

    void updateDataset(EntryInDatasetStats oldEntryInDataset, EntryInDatasetStats entryInDatasetStats);

    DatasetStats getDatasetStatistics();

}
