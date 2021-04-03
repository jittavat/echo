package com.central.test.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EchoDomain {

    private static final String PREFIX = "Hello ";

    private String message;

    public String echo(String responseMsg) {
        return PREFIX + responseMsg;
    }
}
