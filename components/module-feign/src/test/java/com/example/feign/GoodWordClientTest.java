package com.example.feign;

import com.example.feign.config.WebConfig;
import com.example.feign.config.module.JsonMapperJava8DateTimeModule;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GoodWordClientTest {
    GoodWordClient goodWordClient;

    @BeforeEach
    void setUp() {
        goodWordClient = Feign.builder()
                .client(new OkHttpClient())
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

        assertThat(goodWords).isNotNull();
    }

    @Test
    void addGoodWord_callTest() {
        String givenContent = "안녕하세요";

        GoodWordAddResponse response = goodWordClient.addGoodWord(new GoodWordAddRequest(givenContent));

        assertThat(response).isNotNull();
        assertThat(response.getContent()).isEqualTo(givenContent);
    }

    @Test
    void updateGoodWord_callTest() {
        String givenContent = "반가워요";
        GoodWordAddResponse givenResponse = goodWordClient.addGoodWord(new GoodWordAddRequest("안녕하세요"));

        goodWordClient.updateGoodWord(givenResponse.getId(), givenContent);
    }

    @Test
    void deleteGoodWord_callTest() {
        GoodWordAddResponse givenResponse = goodWordClient.addGoodWord(new GoodWordAddRequest("장난이에요"));

        goodWordClient.deleteGoodWord(givenResponse.getId());
    }
}