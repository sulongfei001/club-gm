package com.sevenXnetworks.treasure.exception;

import com.sevenXnetworks.treasure.model.LocalError;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 5009464957746664524L;

    private final String code;

    private final String message;


    public BusinessException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }


    public BusinessException(LocalError localError) {
        this(localError.getCode(), localError.getMessage());
    }


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    public static RuntimeException error(LocalError localError) {
        return new BusinessException(localError);
    }


    public LocalError getLocalError() {
        final BusinessException businessException = this;
        return new LocalError() {
            public String getCode() {
                return businessException.getCode();
            }

            public String getMessage() {
                return businessException.getMessage();
            }
        };
    }
}
