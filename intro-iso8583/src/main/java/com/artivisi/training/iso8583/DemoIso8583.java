package com.artivisi.training.iso8583;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class DemoIso8583 {
    public static void main(String[] xx){

        /*
        BigInteger angka = BigInteger.valueOf(15);
        String angkaBin = angka.toString(2);
        System.out.println("15 binary : "+angkaBin);
        System.out.println("15 hex : "+angka.toString(16));

        BigInteger angka2 = new BigInteger("F", 16);
        System.out.println("F decimal : "+angka2.toString());

        BigInteger bitmap = BigInteger.ZERO;
        System.out.println("bitmap bin : "+bitmap.toString(2));
        System.out.println("bitmap hex : "+bitmap.toString(16));

        bitmap = bitmap.setBit(1);
        bitmap = bitmap.setBit(64 - 41);
        System.out.println("bitmap bin : "+bitmap.toString(2));
        System.out.println("bitmap hex : "+bitmap.toString(16));

        BigInteger bitmap2 = BigInteger.ZERO;
        bitmap2 = bitmap2.setBit(128 - 70);
        System.out.println("bitmap2 bin : "+bitmap2.toString(2));
        System.out.println("bitmap2 hex : "+bitmap2.toString(16));
        */

        
        ISOMessage msg = new ISOMessage("0800");

        /* tidak usah dipanggil manual
        msg.aktifkanSlot(2);
        msg.aktifkanSlot(4);
        msg.aktifkanSlot(7);
        msg.aktifkanSlot(39);
        msg.aktifkanSlot(102);
        */

        msg.isiData(7, "1017110000");
        msg.isiData(41, StringUtils.rightPad("ATM001", 8, " "));
        msg.isiData(70, "301");

        String isomsg = msg.buatMessage();
        System.out.println("ISO Message : "+isomsg);
        
        Map<Integer, Integer> spec = new HashMap<>();
        spec.put(7, 10);
        spec.put(41, 8);
        spec.put(70, 3);

        ISOMessage hasilParsing = ISOMessage.of("0800820000000080000004000000000000001017151200ATM 123 301", spec);

        System.out.println("Bit 7 : ["+ hasilParsing.ambilData(7) +"]");
        System.out.println("Bit 41 : ["+ hasilParsing.ambilData(41) +"]");
        System.out.println("Bit 70 : ["+ hasilParsing.ambilData(70) +"]");

    }
}