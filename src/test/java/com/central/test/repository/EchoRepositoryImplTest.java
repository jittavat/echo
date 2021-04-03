package com.central.test.repository;

import com.central.test.domain.EchoDomain;
import com.central.test.repository.model.EchoMappingResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.net.URI;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EchoRepositoryImplTest {

    private static final String URL = "http://test.com/get";
    private static final String DEFAULT_MESSAGE = "msg";

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private EchoRepositoryImpl echoRepository;
    @Captor
    private ArgumentCaptor<URI> uriArgumentCaptor;

    @BeforeEach
    void setUp() {
        echoRepository.setEchoApi(URL);
    }

    @Nested
    @DisplayName("For call method")
    class CallTest {

        private EchoDomain echoDomain;
        private EchoMappingResponse echoMappingResponse;

        @BeforeEach
        void setUp() {
            echoDomain = EchoDomain.builder()
                    .message(DEFAULT_MESSAGE).build();

            EchoMappingResponse.ArgsMapping args = new EchoMappingResponse.ArgsMapping();
            args.setId(DEFAULT_MESSAGE);
            echoMappingResponse = new EchoMappingResponse();
            echoMappingResponse.setArgs(args);
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "test",
                " "
        })
        @EmptySource
        @DisplayName("when call success then return expect result")
        void success(String msg) {
            echoDomain.setMessage(msg);
            echoMappingResponse.getArgs().setId(msg);
            when(restTemplate.getForEntity(any(URI.class), eq(EchoMappingResponse.class)))
                    .thenReturn(ResponseEntity.ok(echoMappingResponse));
            String actualResponse = echoRepository.call(echoDomain);
            assertEquals(msg, actualResponse);
            verify(restTemplate, times(1)).getForEntity(uriArgumentCaptor.capture(),
                    eq(EchoMappingResponse.class));
            URI actualURI = uriArgumentCaptor.getValue();
            assertEquals("id=" + UriUtils.encode(msg, StandardCharsets.UTF_8),
                    actualURI.getRawQuery());
        }

        @Test
        @DisplayName("when call success then throw Exception")
        void fail() {
            when(restTemplate.getForEntity(any(URI.class), eq(EchoMappingResponse.class)))
                    .thenThrow(RuntimeException.class);
            Executable executable = () -> echoRepository.call(echoDomain);
            assertThrows(RuntimeException.class, executable);
        }
    }
}