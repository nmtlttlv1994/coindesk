package com.homework.coindesk.exception;

/**
 *
 * @author TuanPA44@fpt.com
 */
public class ConstructorException extends RuntimeException {

    public ConstructorException() {
        super("Suppress default constructor for noninstantiability");
    }

}
