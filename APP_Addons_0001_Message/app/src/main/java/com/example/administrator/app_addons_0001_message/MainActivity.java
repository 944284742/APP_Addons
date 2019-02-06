package com.example.administrator.app_addons_0001_message;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Printer;
import android.widget.Button;
import	android.view.View;
import	android.util.Log;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MessageTest";
    private  int ButtonCount = 0;
    private Thread mythread = null;
    private MyThread mythread2 = null;
    private Handler mHandler;
    private  int mMessageCount = 0;

    class MyRunndble implements Runnable {

        @Override
        public void run() {
            int count = 0;
            for (;;){
                Log.d(TAG,"MyThread1 " + count);
                count ++;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    class MyThread extends Thread
    {
        private Looper mLooper;
        public void run()
        {
            super.run();
            Looper.prepare();
            synchronized (this) {
                mLooper = Looper.myLooper();
                notifyAll();
            }
            mLooper =  Looper.myLooper();
            Looper.loop();
        }
        public  Looper getLooper(){
            if (!isAlive()) {
                return null;
            }

            // If the thread has been started, wait until the looper has been created.
            synchronized (this) {
                while (isAlive() && mLooper == null) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
            return mLooper;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     Log.d(TAG,"Send Message"+ButtonCount);
                     ButtonCount++;
                     Message msg = new Message();
                     mHandler.sendMessage(msg);
            }
        });
        mythread = new Thread(new MyRunndble(), "MessageTestThread");
        mythread.start();

        mythread2 = new MyThread();
        mythread2.start();

        mHandler = new Handler(mythread2.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Log.d(TAG,"get Messsage"+mMessageCount);
                mMessageCount++;
                return false;
            }
        });
    }
}
