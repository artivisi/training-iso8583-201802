package com.artivisi.training.jpos.backend.aplikasibackend.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity @Data
public class Pembayaran {
    
    @Id
    private String id;
    
    @ManyToOne @JoinColumn(name = "id_nasabah")
    private Nasabah nasabah;
    
    @NotNull
    private LocalDateTime waktuPembayaran;
    
    @NotNull @Min(0)
    private BigDecimal nilai;
    
    @NotNull @Min(0)
    private BigDecimal fee;
    
    @NotBlank @Size(max = 12, min = 12)
    private String referensi;
}
