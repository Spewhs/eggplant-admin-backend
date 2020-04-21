package eggplant.backend.rabbitmq.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eggplant.backend.dto.prediction.PredictionInformationParams;
import eggplant.backend.service.PredictionInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PredictionConsumer {

    @Autowired
    private PredictionInformationService predictionInformationService;

    public void receiveMessage(byte[] message) {
        PredictionInformationParams predictionInformationParams;

        try {
            predictionInformationParams = new ObjectMapper().readValue(new String(message), PredictionInformationParams.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }

        predictionInformationService.insertNewPrediction(predictionInformationParams);
    }
}
