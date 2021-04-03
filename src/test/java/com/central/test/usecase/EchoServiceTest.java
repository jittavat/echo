package com.central.test.usecase;

import com.central.test.controller.request.EchoRequest;
import com.central.test.controller.response.EchoResponse;
import com.central.test.domain.EchoDomain;
import com.central.test.repository.EchoRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EchoServiceTest {
    private static final String DEFAULT_MESSAGE = "msg";
    @Mock
    private EchoRepository echoRepository;
    @InjectMocks
    private EchoService echoService;

    @Nested
    @DisplayName("For call method")
    class CallTest {
        private EchoRequest echoRequest;
        private EchoDomain echoDomain;

        @BeforeEach
        void setUp() {
            echoRequest = new EchoRequest();
            echoRequest.setMessage(DEFAULT_MESSAGE);

            echoDomain = EchoDomain.builder()
                    .message(DEFAULT_MESSAGE).build();
        }

        @Test
        void success() {
            when(echoRepository.call(echoDomain)).thenReturn(DEFAULT_MESSAGE);
            EchoResponse echoResponse = echoService.call(echoRequest);
            assertEquals("Hello " + DEFAULT_MESSAGE, echoResponse.getResponse());
            verify(echoRepository, times(1)).call(echoDomain);
        }

        @Test
        void fail() {
            when(echoRepository.call(echoDomain)).thenThrow(RuntimeException.class);
            Executable executable = () -> echoService.call(echoRequest);
            assertThrows(RuntimeException.class, executable);
            verify(echoRepository, times(1)).call(echoDomain);
        }

    }
}