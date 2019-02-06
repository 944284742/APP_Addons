package com.example.administrator.app_addons_0001_message;

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
        public void run()
        {
            int count = 0;
            for (;;){
                Log.d(TAG,"MyThread2 " + count);
                count ++;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButton = findViewById(R.id.button);
            mButton.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     Log.d(TAG,"Send Message"+ButtonCount);
                     ButtonCount++;
            }
        });
        mythread = new Thread(new MyRunndble(), "MessageTestThread");
        mythread.start();

        mythread2 = new MyThread();
        mythread2.start();
    }
}
