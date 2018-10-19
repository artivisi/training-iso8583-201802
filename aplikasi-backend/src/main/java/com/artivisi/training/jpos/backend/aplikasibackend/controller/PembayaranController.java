package com.artivisi.training.jpos.backend.aplikasibackend.controller;

import com.artivisi.training.jpos.backend.aplikasibackend.dao.PembayaranDao;
import com.artivisi.training.jpos.backend.aplikasibackend.dto.InquiryRequest;
import com.artivisi.training.jpos.backend.aplikasibackend.dto.InquiryResponseData;
import com.artivisi.training.jpos.backend.aplikasibackend.dto.ResponseData;
import com.artivisi.training.jpos.backend.aplikasibackend.entity.Pembayaran;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PembayaranController {
    
    @Autowired private PembayaranDao pembayaranDao;
    
    @PostMapping("/v1/tbank/topup/inquiry")
    public ResponseData cariByNoHandphone(@RequestBody @Valid InquiryRequest inq){
        
        ResponseData response = new ResponseData();
        
        Pembayaran p = pembayaranDao.findByNasabahNoHandphone(inq.getPhoneNumber());
        if(p != null) {
            InquiryResponseData data = InquiryResponseData.builder()
                    .amount(p.getNilai())
                    .fee(p.getFee())
                    .name(p.getNasabah().getNama())
                    .phoneNumber(p.getNasabah().getNoHandphone())
                    .referenceNumber(p.getReferensi())
                    .transactionId(inq.getTransactionId())
                    .build();
            
            response.setCode("00");
            response.setDescription("Sukses");
            response.setData(data);
            return response;
        }
        
        response.setCode("15");
        response.setDescription("No Handphone belum terdaftar");
        
        return response;
    }
}
