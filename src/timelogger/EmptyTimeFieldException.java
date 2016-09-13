package timelogger;

class EmptyTimeFieldException extends Exception {

    public EmptyTimeFieldException() {
    } 
    
    public EmptyTimeFieldException(String message) {
        super(message);
    }
    
}
