package br.com.commons;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import java.io.IOException;
import java.util.logging.Logger;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        // write your code here

        String token = "f9N6hYQX__U:APA91bEhShvneU18kv1D9UIGsofQZRrs4n4WA5VZQZBMwCCLuVSv8jYmc3b3LYNU4FMefsEL-LBLWiXaUhRfaKQTYhdzroo1ffapBjNqqpPAP1zuDBd7Tr_c733f_hdTZ63rQGnkXz3x";
        Boolean send = sendNotification("Teste envio de notificação", token);

        if(send) {
            logger.info("Success send message!!");
        }
    }

    private static Boolean sendNotification(String message, String appId) {
        Sender sender = new Sender("AIzaSyD0k6MoB7i98iNZqAhqlPoFbal6jZP6Qig"); // Here you will write APP key given by Android end
        Message msg = new Message.Builder()
                .addData("message", message)
                .build();
        boolean send = false;
        try {
            Result results = sender.send(msg, "/topics/global", 5); // Where appId is given by Android end
            if (results.getMessageId() != null) {
                send = Boolean.TRUE;
            } else {
                send = Boolean.FALSE;
                String error = results.getErrorCodeName();
                logger.info("message sending failed:: " + error);
                if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return send;
    }
}
