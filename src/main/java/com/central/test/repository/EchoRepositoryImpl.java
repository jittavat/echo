package com.central.test.repository;

import com.central.test.domain.EchoDomain;
import com.central.test.repository.model.EchoMappingResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Repository
@Slf4j
public class EchoRepositoryImpl implements EchoRepository {

    @Value("${app.echo-api}")
    @Setter
    private String echoApi;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String call(EchoDomain echoDomain) {
        log.info("cache miss");
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(echoApi)
                .queryParam("id", echoDomain.getMessage())
                .build();
        ResponseEntity<EchoMappingResponse> responseEntity = restTemplate
                .getForEntity(uriComponents.toUri(), EchoMappingResponse.class);

        EchoMappingResponse echoMappingResponse = responseEntity.getBody();
        return Objects.requireNonNull(echoMappingResponse).getArgs().getId();
    }
}
