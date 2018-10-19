package com.artivisi.training.jpos.listener;

import org.jpos.iso.ISOException;
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

            if("0800".equals(mti)){
                String bit70 = request.getString(70);
                
                if("301".equals(bit70)) {
                    response = handleEcho(request); 
                } else if("000".equals(bit70)) {
                    log.info("Handle sign on");
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
}

//00570800820000000080000004000000000000001017110000ATM001  301
//00650800C220000080000000010000000000000003123111514440012345603123301