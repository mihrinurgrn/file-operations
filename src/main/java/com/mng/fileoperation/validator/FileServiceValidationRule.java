package com.mng.fileoperation.validator;

import com.mng.fileoperation.domain.BusinessValidationRule;

import java.io.Serializable;

public enum FileServiceValidationRule implements BusinessValidationRule {
    UNEXPECTED_FILE_EXTENSION_ERROR("UNEXPECTED_FILE_EXTENSION_ERROR",
            "This file extension is unexpected");


    private final String code;
    private final String description;

    FileServiceValidationRule(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
