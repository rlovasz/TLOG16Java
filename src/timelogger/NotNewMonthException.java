package timelogger;

public class NotNewMonthException extends Exception {

    public NotNewMonthException() {
    }
    
    public NotNewMonthException(String message) {
         super(message);
    }
    
}
