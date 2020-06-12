package eggplant.backend.service;

import eggplant.backend.dto.stats.EntryInDatasetStats;
import eggplant.backend.model.DatasetStats;
import eggplant.backend.repository.DatasetStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

@Component
public class DatasetStatsServiceImpl implements DatasetStatsService {

    @Autowired
    private DatasetStatsRepository datasetStatsRepository;

    @Override
    public DatasetStats getDatasetStatistics() {
        List<DatasetStats> stats = datasetStatsRepository.findAll();
        DatasetStats datasetStats;
        if (stats.size() <= 0) {
            datasetStats = new DatasetStats(
                    0,
                    new HashMap<>(),
                    new HashMap<>(),
                    new HashMap<>(),
                    new HashMap<>(new HashMap<>()),
                    ZonedDateTime.now()
            );
            datasetStatsRepository.insert(datasetStats);
        } else {
            datasetStats = stats.get(0);
        }
        return datasetStats;
    }

    @Override
    public void addNewEntry(EntryInDatasetStats entryInDatasetStats) {
        DatasetStats datasetStats = getDatasetStatistics();

        datasetStats = updateErrorsDistribution(datasetStats, entryInDatasetStats);
        datasetStats = updateFailStepKeyword(datasetStats, entryInDatasetStats);
        datasetStats = updateCorrectionAction(datasetStats, entryInDatasetStats);
        datasetStats = updateCorrectionErrorAggregation(datasetStats, entryInDatasetStats);

        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        datasetStats.setUpdatedAt(now);

        datasetStatsRepository.insert(datasetStats);
    }

    @Override
    public void updateDataset(EntryInDatasetStats oldEntryInDataset, EntryInDatasetStats newEntryInDatasetStats) {
        DatasetStats datasetStats = getDatasetStatistics();

        datasetStats.getErrorsDistribution().put(
                oldEntryInDataset.getTrainingLabel(),
                datasetStats.getErrorsDistribution().get(oldEntryInDataset.getTrainingLabel()) - 1
        );

        datasetStats.setDatasetSize(datasetStats.getDatasetSize() - 1);


        addNewEntry(newEntryInDatasetStats);
    }

    private DatasetStats updateErrorsDistribution(DatasetStats datasetStats, EntryInDatasetStats entryInDatasetStats) {
        HashMap<String, Integer> errorsDistribution = datasetStats.getErrorsDistribution();
        String error = entryInDatasetStats.getTrainingLabel();

        if (!errorsDistribution.containsKey(error)) {
            errorsDistribution.put(error, 0);
        }

        Integer numberError = errorsDistribution.get(error) + 1;
        errorsDistribution.put(error, numberError);

        datasetStats.setErrorsDistribution(errorsDistribution);

        return datasetStats;
    }

    private DatasetStats updateFailStepKeyword(DatasetStats datasetStats, EntryInDatasetStats entryInDatasetStats) {
        HashMap<String, Integer> failStepDistribution = datasetStats.getFailStepKeyWordDistribution();
        String failStepKeyword = entryInDatasetStats.getFailStepKeyword();

        if(!failStepDistribution.containsKey(failStepKeyword)) {
            failStepDistribution.put(failStepKeyword, 0);
        }

        Integer numberFailStepKeywordEncountered = failStepDistribution.get(failStepKeyword) + 1;
        failStepDistribution.put(failStepKeyword, numberFailStepKeywordEncountered);

        datasetStats.setFailStepKeyWordDistribution(failStepDistribution);

        return datasetStats;
    }

    private DatasetStats updateCorrectionAction(DatasetStats datasetStats, EntryInDatasetStats entryInDatasetStats) {
        HashMap<String, Integer> correctionActionDistribution = datasetStats.getCorrectionActionDistribution();
        String correctionAction = entryInDatasetStats.getCorrectionAction();

        if (!correctionActionDistribution.containsKey(correctionAction)) {
            correctionActionDistribution.put(correctionAction, 1);
        }

        Integer numberOfCorrectionAction = correctionActionDistribution.get(correctionAction) + 1;
        correctionActionDistribution.put(correctionAction, numberOfCorrectionAction);

        datasetStats.setCorrectionActionDistribution(correctionActionDistribution);

        return datasetStats;
    }

    private DatasetStats updateCorrectionErrorAggregation(DatasetStats datasetStats, EntryInDatasetStats entryInDatasetStats) {
        String error = entryInDatasetStats.getTrainingLabel();
        String correctionAction = entryInDatasetStats.getCorrectionAction();
        HashMap<String, HashMap<String, Integer>> correctionErrorAggregation = datasetStats.getCorrectionErrorAggregation();

        if (!correctionErrorAggregation.containsKey(error)) {
            correctionErrorAggregation.put(error, new HashMap<>());
        }

        HashMap<String, Integer> correctionActionRepartition = correctionErrorAggregation.get(error);
        if (!correctionActionRepartition.containsKey(correctionAction)) {
            correctionActionRepartition.put(correctionAction, 0);
        }

        Integer correctionActionTimes = correctionActionRepartition.get(correctionAction) + 1;
        correctionActionRepartition.put(correctionAction, correctionActionTimes);
        correctionErrorAggregation.put(error, correctionActionRepartition);

        datasetStats.setCorrectionErrorAggregation(correctionErrorAggregation);
        return datasetStats;
    }
}
