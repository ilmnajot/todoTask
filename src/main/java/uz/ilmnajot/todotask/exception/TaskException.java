package uz.ilmnajot.todotask.exception;

import org.springframework.http.HttpStatus;

public class TaskException extends BaseException{
    public TaskException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public TaskException(String message) {
        super(message);
    }

    public TaskException(HttpStatus httpStatus) {
        super(httpStatus);
    }
}
