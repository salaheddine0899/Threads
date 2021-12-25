package com.example.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Activity myActivity;
    MyFirstThread t1;
    MySecondThread t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myActivity=this;
    }

    public void buRun(View view) {
        t1=new MyFirstThread();
        t1.start();
        t2=new MySecondThread();
        t2.start();
    }

    public void buStop(View view) {
        t1.interrupt();
        t2.interrupt();
    }

    public void buMessage(View view) {
        Toast.makeText(this, "while thread is working", Toast.LENGTH_LONG).show();
    }

    class MyFirstThread extends Thread{
        private int _x=0;
        final TextView tv1=(TextView) findViewById(R.id.tv1);
        public void run(){
            while (true){
                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv1.setText(String.valueOf(_x));
                    }
                });

                _x++;
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException ex){
                    break;
                }
            }
        }
    }

    class MySecondThread extends Thread{
        private int _x=0;
        final TextView tv2=(TextView) findViewById(R.id.tv2);
        public void run(){
            while (true){
                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv2.setText(String.valueOf(_x));
                    }
                });

                _x++;
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException ex){
                    break;
                }
            }
        }
    }
}