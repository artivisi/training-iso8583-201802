package com.artivisi.training.jpos.backend.aplikasibackend.dto;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InquiryRequest {
    
    @NotBlank
    private String transactionId;
    
    @NotBlank
    private String phoneNumber;
    
    @NotNull @Min(0)
    private BigDecimal amount;
}
