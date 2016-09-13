package timelogger;

class WeekendNotEnabledException extends Exception {

    public WeekendNotEnabledException() {
    }
    
    public WeekendNotEnabledException(String message) {
        super(message);
    }
    
}
