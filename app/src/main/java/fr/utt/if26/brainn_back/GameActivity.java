package fr.utt.if26.brainn_back;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static java.lang.Thread.sleep;

public class GameActivity extends AppCompatActivity {

    View carre1;
    View carre2;
    View carre3;
    private Thread waiting3sec = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Déclaration des composants

        carre1 = (View) findViewById(R.id.carre1);
        carre2 = (View) findViewById(R.id.carre2);
        carre3 = (View) findViewById(R.id.carre3);


        //test du timing
        final  Handler mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // L'avancement se situe dans msg.arg1
                carre1.setBackgroundColor(getResources().getColor(R.color.blue));
            }
        };

        new Thread(new Runnable() {
           public void run() {
               mHandler.sendEmptyMessageDelayed(1, 3000);
           }
        }).start();

    }
}
