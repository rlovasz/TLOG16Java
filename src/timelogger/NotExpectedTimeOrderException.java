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
class NotExpectedTimeOrderException extends Exception {

    public NotExpectedTimeOrderException() {
    }
    
    public NotExpectedTimeOrderException(String message) {
        super(message);
    }
    
}
