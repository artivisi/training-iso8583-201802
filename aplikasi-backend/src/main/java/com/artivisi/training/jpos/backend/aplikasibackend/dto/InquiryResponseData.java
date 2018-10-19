package com.artivisi.training.jpos.backend.aplikasibackend.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data @Builder
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
