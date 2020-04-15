package eggplant.backend.dto.zucchini;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Step {

    @JsonProperty("info")
    private Info info;

    private String status;

    private String errorMessage;

    public Step() {}

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getKeyword() {
        return info.getKeyword();
    }
}
