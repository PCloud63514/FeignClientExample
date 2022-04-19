package com.example.feign;

import com.example.feign.config.WebConfig;
import com.example.feign.config.module.JsonMapperJava8DateTimeModule;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import java.util.List;

class GoodWordClientTest {
    GoodWordClient goodWordClient;

    @BeforeEach
    void setUp() {
        goodWordClient = Feign.builder()
                .decoder(new JacksonDecoder(new WebConfig().objectMapper(new JsonMapperJava8DateTimeModule())))
                .encoder(new JacksonEncoder())
                .contract(new SpringMvcContract())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(GoodWordClient.class, "http://127.0.0.1:8888/good-words");
    }

    @Test
    void getGoodWords_callTest() {
        List<GoodWordGetResponse> goodWords = goodWordClient.getGoodWords();

        Assertions.assertThat(goodWords).isNotNull();
    }
}