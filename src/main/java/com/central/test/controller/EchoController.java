package com.central.test.controller;

import com.central.test.controller.request.EchoRequest;
import com.central.test.controller.response.EchoResponse;
import com.central.test.usecase.EchoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EchoController {

    @Autowired
    private EchoUseCase echoUseCase;

    @GetMapping("/echo")
    public ResponseEntity<EchoResponse> echo(@Valid EchoRequest request) {
        return ResponseEntity.ok(echoUseCase.call(request));
    }
}
