package com.example.feign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GoodWordAddResponse {
    private String id;
    private String content;
}
