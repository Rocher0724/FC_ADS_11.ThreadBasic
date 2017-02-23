package choongyul.android.com.threadbasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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

    boolean flag = true;

    public void runProgram() {
        Thread thread = new CustomThread();
        thread.start();
    }

    public void stopProgram() {
        flag = false;
        System.out.println(flag);
    }

    class CustomThread extends Thread {
        @Override
        public void run () {
            flag = true;
            int i = 0;
            while (flag) {
                i++;
                System.out.println(flag);
                System.out.println("i는   :   " + i);
                if (i % 10000 == 0) {
                    // 메인쓰레드에서 동작
                    final int sec = i;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            result.setText("" + sec / 10000);

                        }
                    });
                }
            }

        }
    }


}