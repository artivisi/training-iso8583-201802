package com.artivisi.training.jpos;

import org.jpos.q2.Q2;

public class AplikasiServer {
    public static void main(String[] xx) {
        Q2 aplikasiJpos = new Q2();
        aplikasiJpos.start();
    }
}