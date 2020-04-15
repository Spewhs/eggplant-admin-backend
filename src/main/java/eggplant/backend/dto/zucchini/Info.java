package eggplant.backend.dto.zucchini;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Info {

    private String keyword;

    public Info() {}

    public String getKeyword() {
        return keyword;
    }
}
