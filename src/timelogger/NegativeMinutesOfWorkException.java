package timelogger;

class NegativeMinutesOfWorkException extends Exception {

    public NegativeMinutesOfWorkException() {
    }
    
    public NegativeMinutesOfWorkException(String message) {
        super(message);
    }
}
