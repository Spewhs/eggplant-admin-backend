package eggplant.backend.model;


import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatasetStat {

    private Integer numberOfEntries;

    // trainingLabel : nb apparition
    private HashMap<String, Integer> errorHistogram;

    // correctionAction : nb apparition
    private HashMap<String, Integer> correctionHistogram;

    // failStepKeyWord : nb apparition
    private HashMap<String, Integer> failStepKeyWordHistogram;

    // trainingLabel: [(correction, nb de fois où cette correction a été prise)]
    private HashMap<String, HashMap<String, Integer>> correctionForError;

    public DatasetStat() {
        numberOfEntries = 0;
        errorHistogram = new HashMap<>();
        correctionHistogram = new HashMap<>();
        failStepKeyWordHistogram = new HashMap<>();
        correctionForError = new HashMap<>();
    }

    public DatasetStat(
            Integer numberOfEntries,
            HashMap<String, Integer> errorHistogram,
            HashMap<String, Integer> correctionHistogram,
            HashMap<String, Integer> failStepKeyWordHistogram,
            HashMap<String, HashMap<String, Integer>> correctionForError
    ) {
        this.numberOfEntries = numberOfEntries;
        this.errorHistogram = errorHistogram;
        this.correctionHistogram = correctionHistogram;
        this.failStepKeyWordHistogram = failStepKeyWordHistogram;
        this.correctionForError = correctionForError;
    }

    void addTrainingLabel(String label) {
        if (!errorHistogram.containsKey(label)) {
            errorHistogram.put(label, 0);
        }
        errorHistogram.replace(label, errorHistogram.get(label) + 1);
    }

    void addCorrectionAction(String correction) {
        if (!correctionHistogram.containsKey(correction)) {
            correctionHistogram.put(correction, 0);
        }
        correctionHistogram.replace(correction, correctionHistogram.get(correction));
    }

    void addFailStepKeyWord(String keyword) {
        if (!failStepKeyWordHistogram.containsKey(keyword)) {
            failStepKeyWordHistogram.put(keyword, 0);
        }
        failStepKeyWordHistogram.replace(keyword, failStepKeyWordHistogram.get(keyword) + 1);
    }

    void addCorrectionForError(String errorLabel, String correction) {
        if (!correctionForError.containsKey(errorLabel)) {
            correctionForError.put(errorLabel, new HashMap<>());
        }
        HashMap<String, Integer> correctionMap = correctionForError.get(errorLabel);
        if (!correctionMap.containsKey(correction)) {
            correctionMap.put(correction, 0);
        }
        correctionMap.replace(correction, correctionMap.get(correction) + 1);
        correctionForError.replace(errorLabel, correctionMap);
    }
}
