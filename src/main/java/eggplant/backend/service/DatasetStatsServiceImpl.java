package eggplant.backend.service;

import eggplant.backend.dto.stats.NewEntryInDataset;
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
    public void addNewEntry(NewEntryInDataset newEntryInDataset) {
        DatasetStats datasetStats = getDatasetStatistics();

        datasetStats = updateErrorsDistribution(datasetStats, newEntryInDataset);
        datasetStats = updateFailStepKeyword(datasetStats, newEntryInDataset);
        datasetStats = updateCorrectionAction(datasetStats, newEntryInDataset);
        datasetStats = updateCorrectionErrorAggregation(datasetStats, newEntryInDataset);

        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        datasetStats.setUpdatedAt(now);

        datasetStatsRepository.insert(datasetStats);
    }

    private DatasetStats updateErrorsDistribution(DatasetStats datasetStats, NewEntryInDataset newEntryInDataset) {
        HashMap<String, Integer> errorsDistribution = datasetStats.getErrorsDistribution();
        String error = newEntryInDataset.getTrainingLabel();

        if (!errorsDistribution.containsKey(error)) {
            errorsDistribution.put(error, 0);
        }

        Integer numberError = errorsDistribution.get(error) + 1;
        errorsDistribution.put(error, numberError);

        datasetStats.setErrorsDistribution(errorsDistribution);

        return datasetStats;
    }

    private DatasetStats updateFailStepKeyword(DatasetStats datasetStats, NewEntryInDataset newEntryInDataset) {
        HashMap<String, Integer> failStepDistribution = datasetStats.getFailStepKeyWordDistribution();
        String failStepKeyword = newEntryInDataset.getFailStepKeyword();

        if(!failStepDistribution.containsKey(failStepKeyword)) {
            failStepDistribution.put(failStepKeyword, 0);
        }

        Integer numberFailStepKeywordEncountered = failStepDistribution.get(failStepKeyword) + 1;
        failStepDistribution.put(failStepKeyword, numberFailStepKeywordEncountered);

        datasetStats.setFailStepKeyWordDistribution(failStepDistribution);

        return datasetStats;
    }

    private DatasetStats updateCorrectionAction(DatasetStats datasetStats, NewEntryInDataset newEntryInDataset) {
        HashMap<String, Integer> correctionActionDistribution = datasetStats.getCorrectionActionDistribution();
        String correctionAction = newEntryInDataset.getCorrectionAction();

        if (!correctionActionDistribution.containsKey(correctionAction)) {
            correctionActionDistribution.put(correctionAction, 1);
        }

        Integer numberOfCorrectionAction = correctionActionDistribution.get(correctionAction) + 1;
        correctionActionDistribution.put(correctionAction, numberOfCorrectionAction);

        datasetStats.setCorrectionActionDistribution(correctionActionDistribution);

        return datasetStats;
    }

    private DatasetStats updateCorrectionErrorAggregation(DatasetStats datasetStats, NewEntryInDataset newEntryInDataset) {
        String error = newEntryInDataset.getTrainingLabel();
        String correctionAction = newEntryInDataset.getCorrectionAction();
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
