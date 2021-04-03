package com.central.test.usecase;

import com.central.test.controller.request.EchoRequest;
import com.central.test.controller.response.EchoResponse;

public interface EchoUseCase {

    EchoResponse call(EchoRequest request);
}
