package com.artivisi.training.jpos.listener;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;
import org.jpos.util.Log;
import org.jpos.q2.Q2;

public class EchoListener implements ISORequestListener {

    private Log log;

    public EchoListener(){
        log = Log.getLog(Q2.LOGGER_NAME, "listener");
    }

    public boolean process(ISOSource sender, ISOMsg request) {
        try {
            log.info("Menerima message");
            String mti = request.getMTI();
            log.info("MTI : "+mti);

            ISOMsg response = (ISOMsg) request.clone();
            response.setMTI("0810");
            response.set(39, "00");
            sender.send(response);

            return true;
        } catch (Exception err) {
            err.printStackTrace();
        } 
        return false;
    }
}

//00570800820000000080000004000000000000001017110000ATM001  301
//00650800C220000080000000010000000000000003123111514440012345603123301