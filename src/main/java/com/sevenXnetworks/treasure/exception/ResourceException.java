package com.sevenXnetworks.treasure.exception;

import com.sevenXnetworks.treasure.model.LocalError;

public class ResourceException extends RuntimeException {

    private static final long serialVersionUID = 5009464957746664524L;

    private final String code;

    private final String message;

    public ResourceException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ResourceException(LocalError localError) {
        this(localError.getCode(), localError.getMessage());
    }


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    public static ResourceException error(LocalError localError) {
        return new ResourceException(localError);
    }


    public LocalError getLocalError() {
        final ResourceException resourceException = this;
        return new LocalError() {
            public String getCode() {
                return resourceException.getCode();
            }

            public String getMessage() {
                return resourceException.getMessage();
            }
        };
    }
}
