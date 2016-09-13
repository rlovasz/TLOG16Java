package timelogger;

class InvalidTaskIdException extends Exception {

    public InvalidTaskIdException() {
    }
    
    public InvalidTaskIdException(String message) {
        super(message);
    }
}
