package com.artivisi.training.jpos.listener;

import java.math.BigDecimal;

import com.artivisi.training.jpos.dto.InquiryRequest;
import com.artivisi.training.jpos.dto.InquiryResponseData;
import com.artivisi.training.jpos.dto.ResponseData;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;
import org.jpos.q2.Q2;
import org.jpos.util.Log;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class EchoListener implements ISORequestListener {

    private Log log;
    private String serverUrl = "http://localhost:8080";
    private WebClient webClient;

    public EchoListener(){
        webClient = WebClient.create(serverUrl);
        log = Log.getLog(Q2.LOGGER_NAME, "listener");
    }

    public boolean process(ISOSource sender, ISOMsg request) {
        try {
            log.info("Menerima message");
            String mti = request.getMTI();
            log.info("MTI : "+mti);
            ISOMsg response = (ISOMsg) request.clone();

            if("0800".equals(mti)){
                String bit70 = request.getString(70);
                
                if("301".equals(bit70)) {
                    response = handleEcho(request); 
                } else if("000".equals(bit70)) {
                    log.info("Handle sign on");
                }
            } else if("0200".equals(mti)) {
                String noHp = request.getString(2);
                BigDecimal amount = new BigDecimal(request.getString(4));

                ResponseData hasil = backendInquiry(noHp, amount);

                response.setMTI("0210");
                if("00".equals(hasil.getCode())) {
                    InquiryResponseData responseData = 
                    (InquiryResponseData)hasil.getData();

                    response.set(39, "00");
                    response.set(48, "["+responseData.getName()+"]-["+responseData.getReferenceNumber()+"]");
                } else {
                    // nantinya ambil mapping response code dari backend ke response code kita
                    // misalnya RC 15 (backend) menjadi RC 55 
                    response.set(39, hasil.getCode());
                }
            }
                   
            // tambahkan if untuk jenis mti lain

            sender.send(response);
            return true;
        } catch (Exception err) {
            err.printStackTrace();
        } 
        return false;
    }

    private ISOMsg handleEcho(ISOMsg request) throws ISOException {
        ISOMsg response = (ISOMsg) request.clone();
        response.setMTI("0810");
        response.set(39, "00");
        return response;
    }

    private ResponseData backendInquiry(String noHp, BigDecimal amount){
        log.info("Query transaksi "+noHp+" ke "+serverUrl);

        InquiryRequest request = InquiryRequest.builder()
        .phoneNumber(noHp)
        .amount(amount)
        .transactionId("123456789012")
        .build();

        Mono<ResponseData> responseMono = webClient
            .post().uri("/v1/tbank/topup/inquiry")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(request)
                .retrieve()
                .bodyToMono(ResponseData.class);
        ResponseData hasil = responseMono.block();
        log.info(hasil);
        return hasil;
    }
}

//00570800820000000080000004000000000000001017110000ATM001  301
//00650800C220000080000000010000000000000003123111514440012345603123301