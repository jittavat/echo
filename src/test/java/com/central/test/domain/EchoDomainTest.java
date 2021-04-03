package com.central.test.domain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EchoDomainTest {

    private static final String DEFAULT_MESSAGE = "msg";

    private EchoDomain echoDomain;

    @BeforeEach
    void setUp() {
        echoDomain = EchoDomain.builder()
                .message(DEFAULT_MESSAGE)
                .build();

    }

    @Nested
    @DisplayName("For echo method")
    class EchoTest {
        @ParameterizedTest
        @ValueSource(strings = {
                "world",
                " ",
                "\n"
        })
        @NullAndEmptySource
        @DisplayName("when call success then return expect result")
        void success(String msg) {
            String expectMessage = "Hello " + msg;
            String actualMessage = echoDomain.echo(msg);
            assertEquals(expectMessage, actualMessage);
        }
    }
}