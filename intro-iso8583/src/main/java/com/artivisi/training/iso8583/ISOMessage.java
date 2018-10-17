package com.artivisi.training.iso8583;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ISOMessage {
    private BigInteger primaryBitmap = BigInteger.ZERO;
    private BigInteger secondaryBitmap = BigInteger.ZERO;
    private Map<Integer, String> data = new HashMap<>();

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

    public String hitungBitmap(){
        String primary = StringUtils.leftPad(primaryBitmap.toString(16), 16, "0");
        String secondary = StringUtils.leftPad(secondaryBitmap.toString(16), 16, "0");
        return primary + secondary;
    }
}