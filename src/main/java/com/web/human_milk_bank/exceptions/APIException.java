package com.web.human_milk_bank.exceptions;

import java.io.Serial;

public class APIException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public APIException() {
    }

    public APIException(String message) {
        super(message);
    }
}
