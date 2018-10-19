package com.artivisi.training.jpos.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class InquiryResponseData {
    private String referenceNumber;
    private String transactionId;
    private String phoneNumber;
    private String name;
    private BigDecimal amount;
    private BigDecimal fee;
    
    public BigDecimal getTotal(){
        return amount.add(fee);
    }
}