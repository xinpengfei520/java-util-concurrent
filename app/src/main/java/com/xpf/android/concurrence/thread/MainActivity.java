package com.xpf.android.concurrence.thread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //runnableTest();
        threadTest();
    }

    private void runnableTest() {
        try {
            LockRunnable runnable1 = new LockRunnable();
            Thread thread1 = new Thread(runnable1);

            LockRunnable runnable2 = new LockRunnable();
            Thread thread2 = new Thread(runnable2);

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            Log.i(TAG, "i===" + LockRunnable.i);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void threadTest() {

        final VolatileSafe volatileSafe = new VolatileSafe();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "线程1开始执行...");
                volatileSafe.print();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "线程2开始执行...");
                volatileSafe.increase();
            }
        }).start();
    }
}
