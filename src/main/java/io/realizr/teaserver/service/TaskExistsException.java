package io.realizr.teaserver.service;

public class TaskExistsException extends Exception {

    public TaskExistsException() {
        super();
    }

    public TaskExistsException(String message) {
        super(message);
    }

    public TaskExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskExistsException(Throwable cause) {
        super(cause);
    }

    protected TaskExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
