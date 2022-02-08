package com.mng.fileoperation.domain;

import java.io.Serializable;

public interface BusinessValidationRule extends Serializable {
    String getCode();

    String getDescription();
}
