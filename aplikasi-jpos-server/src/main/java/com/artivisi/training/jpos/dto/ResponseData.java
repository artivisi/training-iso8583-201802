package com.artivisi.training.jpos.dto;

import lombok.Data;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@Data @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResponseData {
    private String code;
    private String description;
    private InquiryResponseData data;
}