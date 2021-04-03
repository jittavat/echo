package com.central.test.repository;

import com.central.test.domain.EchoDomain;
import org.springframework.cache.annotation.Cacheable;

public interface EchoRepository {
    @Cacheable(value = "echo-cache",
            key = "{ #echoDomain?.message }")
    String call(EchoDomain echoDomain);
}
