package com.demo.mail;

import com.demo.common.model.Mail;

/**
 * Created by nilszhang on 2016/3/22.
 */
public class Mailtask implements Runnable {

    public void run() {
        try {
            Mail.dao.sendWaitedMail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
