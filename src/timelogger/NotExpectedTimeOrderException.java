package timelogger;

class NotExpectedTimeOrderException extends Exception {

    public NotExpectedTimeOrderException() {
    }
    
    public NotExpectedTimeOrderException(String message) {
        super(message);
    }
    
}
