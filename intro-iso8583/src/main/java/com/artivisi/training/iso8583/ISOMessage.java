package com.artivisi.training.iso8583;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ISOMessage {
    private BigInteger primaryBitmap = BigInteger.ZERO;
    private BigInteger secondaryBitmap = BigInteger.ZERO;
    private Map<Integer, String> data = new HashMap<>();
    private String mti;

    public ISOMessage(String mti){
        this.mti = mti;
    }

    public void aktifkanSlot(Integer nomor){
        if(nomor > 64) {
            // karena ada bit > 64 yang aktif, 
            // maka secondary bitmap aktif
            // ditandai dengan bit 1 = on
            primaryBitmap = primaryBitmap.setBit(64 - 1);
            secondaryBitmap = secondaryBitmap.setBit(128 - nomor);
        } else {
            primaryBitmap = primaryBitmap.setBit(64 - nomor);
        }
    }

    private String hitungBitmap(){
        String primary = StringUtils.leftPad(primaryBitmap.toString(16), 16, "0");
        String secondary = StringUtils.leftPad(secondaryBitmap.toString(16), 16, "0");
        return primary + secondary;
    }

    public void isiData(Integer slot, String isi){
        data.put(slot, isi);
    }

    public String buatMessage(){
        StringBuilder hasil = new StringBuilder();

        for(int i = 0; i < 128; i++){
            String isi = data.get(i);
            if(isi != null) {
                aktifkanSlot(i);
                hasil.append(isi);
            }
        }

        String bitmap = hitungBitmap();
        return mti + bitmap + hasil;
    }

    public String ambilData(Integer slot) {
        return data.get(slot);
    }

    public static ISOMessage of(String msg, Map<Integer, Integer> spec){
        Integer posisiParsing = 0;
        String mti = msg.substring(0, 4);
        posisiParsing += 4;

        System.out.println("MTI : "+mti);

        String strPrimaryBitmap = msg.substring(posisiParsing, posisiParsing + 16);
        posisiParsing += 16;
        System.out.println("Primary  : "+strPrimaryBitmap);
        
        BigInteger primaryBitmap = new BigInteger(strPrimaryBitmap, 16);

        BigInteger secondaryBitmap = BigInteger.ZERO;
        Boolean adaSecondaryBitmap = primaryBitmap.testBit(64 - 1);
        if(adaSecondaryBitmap) {
            String strSecondary = msg.substring(posisiParsing, posisiParsing + 16);
            secondaryBitmap = new BigInteger(strSecondary, 16);
            posisiParsing += 16;
        }
        ISOMessage hasil = new ISOMessage(mti);

        for(int i = 2; i < 65; i++) {
            if(primaryBitmap.testBit(64 - i)){
                System.out.println("i : "+i);
                Integer panjangData = spec.get(i);
                hasil.isiData(i, msg.substring(posisiParsing, posisiParsing + panjangData));
                posisiParsing += panjangData;
            }
        }

        if(adaSecondaryBitmap) {
            for(int i = 65; i < 129; i++){
                if(secondaryBitmap.testBit(128 - i)){
                    System.out.println("i : "+i);
                    Integer panjangData = spec.get(i);
                    hasil.isiData(i, msg.substring(posisiParsing, posisiParsing + panjangData));
                    posisiParsing += panjangData;
                }
            }
        }
        
        return hasil;
    }
}