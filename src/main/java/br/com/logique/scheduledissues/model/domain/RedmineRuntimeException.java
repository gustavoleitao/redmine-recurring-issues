package br.com.logique.scheduledissues.model.domain;

/**
 * Created by Gustavo on 17/04/2016.
 */
public class RedmineRuntimeException extends RuntimeException {

    public RedmineRuntimeException() {
    }

    public RedmineRuntimeException(String message) {
        super(message);
    }

    public RedmineRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedmineRuntimeException(Throwable cause) {
        super(cause);
    }

    public RedmineRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
