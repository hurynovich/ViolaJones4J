package io.github.hurynovich.vj4j.detector.api;


public class DetectorException extends RuntimeException {
    public DetectorException(String message) {
        super(message);
    }

    public DetectorException(String message, Throwable cause) {
        super(message, cause);
    }

    public DetectorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
