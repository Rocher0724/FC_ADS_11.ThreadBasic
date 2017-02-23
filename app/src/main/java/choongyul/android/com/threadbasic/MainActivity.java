package choongyul.android.com.threadbasic;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int SET_TEXT = 100;
    Button btnStart, btnStop, btnRap;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.tvResult);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runProgram();
            }
        });
        btnStop = (Button) findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopProgram();
            }
        });
        btnRap = (Button) findViewById(R.id.btnRap);

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SET_TEXT:
                    result.setText(msg.arg1 + "");
            }
        }

    };
    boolean flag = false;

    public void runProgram() {
        if(!flag) {
            Thread thread = new CustomThread();
            thread.start();
        } else {
            Toast.makeText(this, "실행중입니다.", Toast.LENGTH_SHORT).show();
        }
    }

    public void stopProgram() {
        flag = false;
        System.out.println(flag);
    }

    class CustomThread extends Thread {
        @Override
        public void run () {
            flag = true;
            int sec = 0;
            while (flag) {

                // 1. 메시지와 데이터를 보낼 때
                Message msg = new Message();
                msg.what = SET_TEXT;
                msg.arg1 = sec;
                handler.sendMessage(msg);

                // 2. 데이터만 보낼 때
//                handler.sendEmptyMessage(SET_TEXT);

                sec++;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }



}