package org.exception;

/**
 * Created by Administrator on 16.11.17.
 */
public class SeckillException extends Exception {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
