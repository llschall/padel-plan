package org.llschall.padel;

class PadelPlanException extends RuntimeException {

    PadelPlanException(String message) {
        super(message);
    }

    PadelPlanException(Exception cause) {
        super(cause);
    }


}
