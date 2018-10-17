package com.artivisi.training.iso8583;

import java.math.BigInteger;

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

        
        ISOMessage msg = new ISOMessage();
        msg.aktifkanSlot(7);
        msg.aktifkanSlot(41);
        msg.aktifkanSlot(70);

        String bitmap = msg.hitungBitmap();
        System.out.println("Bitmap : "+bitmap);
        
    }
}