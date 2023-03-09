package com.blog.project.springbootwebservice.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor//final 필드 생성자 생성
public class HelloResponseDto {

    private final String name;
    private final int amount;
}

