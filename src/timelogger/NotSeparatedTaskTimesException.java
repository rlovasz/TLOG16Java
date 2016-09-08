/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timelogger;

/**
 *
 * @author precognox
 */
class NotSeparatedTaskTimesException extends Exception {

    public NotSeparatedTaskTimesException() {
    }
    
    public NotSeparatedTaskTimesException(String message) {
        super(message);
    }
}
