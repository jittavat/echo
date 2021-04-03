package com.central.test.controller.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EchoRequest {
    @NotNull(message = "message must not be null")
    private String message;
}
