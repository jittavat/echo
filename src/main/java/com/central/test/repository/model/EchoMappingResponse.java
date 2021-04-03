package com.central.test.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EchoMappingResponse {
    private ArgsMapping args;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ArgsMapping {
        private String id;
    }

}
