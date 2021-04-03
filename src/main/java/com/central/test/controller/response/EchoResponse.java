package com.central.test.controller.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EchoResponse {
    private final String response;
}
