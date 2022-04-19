package com.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@FeignClient(name = "good-word", url = "localhost:8888/good-words")
public interface GoodWordClient {
    @PostMapping
    void addGoodWord(@Valid @RequestBody GoodWordAddRequest request);

    @GetMapping
    List<GoodWordGetResponse> getGoodWords();

    @PatchMapping("{id}")
    void updateGoodWord(@PathVariable UUID id, @RequestParam String content);

    @DeleteMapping("{id}")
    void deleteGoodWord(@PathVariable UUID id);
}
