package com.naksam.walletserver.feign;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

import static java.lang.String.format;

public class FeignErrorDecode implements ErrorDecoder {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Exception decode(String methodKey, Response response) {
        logger.error(format("%s 요청이 성공하지 못했습니다. status: %s requestUrl: %s, requestBody: %s, responseBody: %s",
                methodKey, response.status(), response.request().url(), Arrays.toString(response.request().body()), response.body()));

        if (isRetry(response)) {
            return new RetryableException(response.status(), format("%s 요청이 성공하지 못했습니다. Retry 합니다. - status: %s, headers: %s", methodKey, response.status(), response.headers()),
                    response.request().httpMethod(), Date.from(Instant.now()), response.request());
        }

        return new IllegalStateException(format("%s 요청이 성공하지 못했습니다. - cause: %s, headers: %s", methodKey, response.status(), response.headers()));
    }

    private boolean isRetry(Response response) {
        return response.request()
                .httpMethod()
                .name()
                .equalsIgnoreCase("GET");
    }
}