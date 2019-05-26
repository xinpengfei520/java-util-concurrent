package com.xpf.android.concurrence.thread;

import android.util.Log;

/**
 * Created by x-sir on 2019-05-26 :)
 * Function:
 */
public class VolatileSafe {

    private static final String TAG = "VolatileSafe";
    /**
     * 理解 volatile 关键字
     */
    private volatile boolean i;

    public VolatileSafe() {
    }

    public void increase() {
        i = true;
    }

    public void print() {
        while (!i) {
            Log.i(TAG, "i:" + i);
        }
    }

}
