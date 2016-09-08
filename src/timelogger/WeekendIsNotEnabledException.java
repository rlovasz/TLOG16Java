package timelogger;

class WeekendIsNotEnabledException extends Exception {

    public WeekendIsNotEnabledException() {
    }
    
    public WeekendIsNotEnabledException(String message) {
        super(message);
    }
    
}
