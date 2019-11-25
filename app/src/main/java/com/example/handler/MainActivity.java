package com.example.handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button change;
    private TextView text;
    public static final int UPDATE_TEXT=1;
    private Handler handler=new Handler(){
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case UPDATE_TEXT:
                    text.setText("handler改变了信息");
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        change=(Button)findViewById(R.id.handler);
        text=(TextView)findViewById(R.id.text);
        change.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message=new Message();
                message.what=UPDATE_TEXT;
                handler.sendMessage(message);
            }
        }).start();
    }
}
