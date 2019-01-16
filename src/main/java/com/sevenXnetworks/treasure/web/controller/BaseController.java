package com.sevenXnetworks.treasure.web.controller;

import com.sevenXnetworks.treasure.exception.ParameterException;
import com.sevenXnetworks.treasure.model.LocalError;

public class BaseController {
    protected ParameterException error(LocalError localError) {
        return ParameterException.error(localError);
    }
}
