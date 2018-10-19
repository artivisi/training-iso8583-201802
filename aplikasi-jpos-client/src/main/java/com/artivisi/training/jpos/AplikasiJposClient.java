package com.artivisi.training.jpos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang.StringUtils;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.GenericPackager;

public class AplikasiJposClient {
    public static void main(String[] args) throws Exception {
        GenericPackager packager = new GenericPackager(AplikasiJposClient.class.getResourceAsStream("/training.xml"));
        ASCIIChannel channel = new ASCIIChannel("localhost", 10000, packager);
        channel.connect();
        
        //ISOMsg request = createEchoRequest(packager);
        ISOMsg request = createInquiryRequest(packager, "081234567890", new BigDecimal("100000"));
        channel.send(request);
        ISOMsg response = channel.receive();
        System.out.println("Response : ["+new String(response.pack())+"]");
        
        channel.disconnect();
    }
    
    private static ISOMsg createInquiryRequest(GenericPackager packager, String noHp, BigDecimal amount) throws ISOException {
        ISOMsg request = new ISOMsg("0200");
        request.set(2, noHp);
        request.set(4, StringUtils.leftPad(amount.setScale(0, RoundingMode.HALF_EVEN).toString(), 12, "0"));
        request.setPackager(packager);
        String req = new String(request.pack());
        System.out.println("Request : ["+req+"]");
        return request;
    }

    private static ISOMsg createEchoRequest(GenericPackager packager) throws ISOException {
        // buat message
        ISOMsg echoRequest = new ISOMsg("0800");
        echoRequest.set(7, LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmmss")));
        echoRequest.set(41, StringUtils.rightPad("CL001", 8, " "));
        echoRequest.set(70, "301");
        // buat debug, kita tampilkan dulu
        echoRequest.setPackager(packager);
        String req = new String(echoRequest.pack());
        System.out.println("Echo Request : ["+req+"]");
        return echoRequest;
    }
}
