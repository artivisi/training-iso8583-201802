package com.artivisi.training.jpos.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Data;

@Data @Builder @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class InquiryRequest {
    private String transactionId;
    private String phoneNumber;
    private BigDecimal amount;
}
