package com.demo;

import com.demo.common.model.Xianyu;
import com.demo.common.model.ZkPosts;

/**
 * Created by nilszhang on 2016/3/22.
 */
public class Zktask implements Runnable {

    public void run() {
        try {
            ZkPosts.dao.fetchNewInfo();
            Xianyu.dao.fetchNewInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
