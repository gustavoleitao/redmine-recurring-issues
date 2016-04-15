package br.com.logique.scheduledissues.model.domain;

/**
 * Created by gustavo on 14/04/2016.
 */
public class ResponseMsg {

    private String message;

    public ResponseMsg(String message, String... args) {
        this.message = String.format(message, args);
    }

    public ResponseMsg(Exception e) {
        this.message = e.getMessage();
    }

    public String getMessage() {
        return this.message;
    }
}