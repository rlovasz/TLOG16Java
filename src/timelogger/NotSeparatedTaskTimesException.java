package timelogger;

class NotSeparatedTaskTimesException extends Exception {

    public NotSeparatedTaskTimesException() {
    }
    
    public NotSeparatedTaskTimesException(String message) {
        super(message);
    }
}
