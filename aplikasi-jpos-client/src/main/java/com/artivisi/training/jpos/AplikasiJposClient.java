package com.artivisi.training.jpos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang.StringUtils;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.GenericPackager;

public class AplikasiJposClient {
    public static void main(String[] args) throws Exception {
        GenericPackager packager = new GenericPackager(AplikasiJposClient.class.getResourceAsStream("/training.xml"));
        ASCIIChannel channel = new ASCIIChannel("localhost", 10000, packager);
        channel.connect();
        
        // buat message
        ISOMsg echoRequest = new ISOMsg("0800");
        echoRequest.set(7, LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmmss")));
        echoRequest.set(41, StringUtils.rightPad("CL001", 8, " "));
        echoRequest.set(70, "301");
        
        // buat debug, kita tampilkan dulu
        echoRequest.setPackager(packager);
        String req = new String(echoRequest.pack());
        System.out.println("Echo Request : ["+req+"]");
        
        channel.send(echoRequest);
        ISOMsg response = channel.receive();
        System.out.println("Echo Response : ["+new String(response.pack())+"]");
        
        channel.disconnect();
    }
}
