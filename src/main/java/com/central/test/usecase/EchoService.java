package com.central.test.usecase;

import com.central.test.controller.request.EchoRequest;
import com.central.test.controller.response.EchoResponse;
import com.central.test.domain.EchoDomain;
import com.central.test.repository.EchoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EchoService implements EchoUseCase {
    @Autowired
    private EchoRepository echoRepository;

    @Override
    public EchoResponse call(EchoRequest request) {
        EchoDomain echoDomain = EchoDomain.builder()
                .message(request.getMessage()).build();
        String responseEchoRepository = echoRepository.call(echoDomain);
        String echoResult = echoDomain.echo(responseEchoRepository);
        return EchoResponse.builder()
                .response(echoResult).build();
    }
}
