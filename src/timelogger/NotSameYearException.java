package timelogger;

public class NotSameYearException extends Exception {

    public NotSameYearException() {
    }
    
    public NotSameYearException(String message) {
        super(message);
    }
}
