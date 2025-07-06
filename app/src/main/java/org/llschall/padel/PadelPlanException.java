package org.llschall.padel;

public class PadelPlanException extends RuntimeException {

    public PadelPlanException(String message) {
        super(message);
    }

    PadelPlanException(Exception cause) {
        super(cause);
    }


}
