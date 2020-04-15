package eggplant.backend.dto.zucchini;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Analysis {

    private String action;

    private String result;

    public Analysis() {}

    public Analysis(
            String action,
            String result
    ) {
        this.action = action;
        this.result = result;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
