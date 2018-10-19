package com.artivisi.training.jpos.backend.aplikasibackend.dto;

import lombok.Data;

@Data
public class ResponseData {
    private String code;
    private String description;
    private Object data;
}
