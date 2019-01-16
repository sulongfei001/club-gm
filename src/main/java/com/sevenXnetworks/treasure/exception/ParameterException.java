package com.sevenXnetworks.treasure.exception;

import com.sevenXnetworks.treasure.model.LocalError;

public class ParameterException extends RuntimeException {

    private static final long serialVersionUID = 5009464957746664524L;

    private final String code;

    private final String message;

    public ParameterException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ParameterException(LocalError localError) {
        this(localError.getCode(), localError.getMessage());
    }


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    public static ParameterException error(LocalError localError) {
        return new ParameterException(localError);
    }


    public LocalError getLocalError() {
        final ParameterException parameterException = this;
        return new LocalError() {
            public String getCode() {
                return parameterException.getCode();
            }

            public String getMessage() {
                return parameterException.getMessage();
            }
        };
    }

}
