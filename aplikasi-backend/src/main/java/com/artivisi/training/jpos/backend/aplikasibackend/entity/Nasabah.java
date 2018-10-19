package com.artivisi.training.jpos.backend.aplikasibackend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Entity @Data
public class Nasabah {
    
    @Id
    private String id;
    
    @NotBlank
    private String noHandphone;
    
    @NotBlank
    private String nama;
}
