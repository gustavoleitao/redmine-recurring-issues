package br.com.logique.scheduledissues.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Gustavo on 23/04/2016.
 */
public class ValidateResponse {

    @JsonProperty("isValid")
    private boolean isValid;

    private String value;

    private ValidateResponse(boolean isValid, String value) {
        this.isValid = isValid;
        this.value = value;
    }

    public static ValidateResponse of(boolean isValid, String value){
        return new ValidateResponse(isValid, value);
    }

    @JsonIgnore
    public boolean isValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
