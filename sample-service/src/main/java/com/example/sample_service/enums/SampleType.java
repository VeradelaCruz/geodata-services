package com.example.sample_service.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum SampleType {
    SOIL,
    ROCK,
    WATER,
    MINERAL,
    OTHER
}
