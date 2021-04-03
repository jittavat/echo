package com.central.test.controller.request;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.central.test.controller.request.ValidatorTestHelper.assertConstraintFieldFail;
import static com.central.test.controller.request.ValidatorTestHelper.assertConstraintFieldSuccess;

class EchoRequestTest {

    private static final String DEFAULT_MESSAGE = "msg";

    private EchoRequest echoRequest;

    @BeforeEach
    void setUp() {
        echoRequest = new EchoRequest();
        echoRequest.setMessage(DEFAULT_MESSAGE);
    }

    @Nested
    @DisplayName("For message field")
    class MessageTest {

        private final String expectField = "message";

        @ParameterizedTest
        @ValueSource(strings = {
                "hello",
                "\r\n"
        })
        @EmptySource
        @DisplayName("when call success then not found validation error")
        void success(String msg) {
            echoRequest.setMessage(msg);
            assertConstraintFieldSuccess(echoRequest);
        }

        @Test
        @DisplayName("when call fail then found validation error")
        void fail() {
            echoRequest.setMessage(null);
            assertConstraintFieldFail(echoRequest, expectField,
                    "message must not be null");
        }
    }
}