package com.example.harsh.scarnedicee;

import android.drm.DrmStore;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int utotal=0;
    private int utscore=0;
    private int ctotal;
    private int ctscore;
    private boolean isuser=true;
    private TextView textview;
    private ImageView img;
    private Button roll;
    private Button hold;
    private Button reset;
    private TextView textview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview=(TextView)findViewById(R.id.textView);
        img=(ImageView)findViewById(R.id.imageView);
        roll=(Button)findViewById(R.id.button4);
        hold=(Button)findViewById(R.id.button5);
        reset=(Button)findViewById(R.id.button6);
        textview2=(TextView)findViewById(R.id.textView2);
        roll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    if (isuser == false) {
                        return;
                    }
                    Random rand = new Random();
                    int[] res = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
                    int rndInt = rand.nextInt(res.length);
                    img.setImageDrawable(getResources().getDrawable(res[rndInt]));

                    if (rndInt == 0) {
                        isuser = false;
                        textview.setText("Player score:" + utotal + " Computer Score: " + ctotal);

                        textview2.setText("0");
                        utscore = 0;
                        ctscore = 0;
                        Toast.makeText(MainActivity.this, "Computer Turn", Toast.LENGTH_LONG).show();
                        h.postDelayed(r,1000);
                    } else {
                        utscore += rndInt + 1;

                        textview2.setText("" + utscore);
                        //   textview.setText("Player score:"+utscore+" Computer Score:0");
                    }

            }
        });
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (utotal >= 100) {
                    Toast.makeText(MainActivity.this, "PLayer Wins", Toast.LENGTH_SHORT).show();
                    textview.setText("Player Score: 0 Computer Score: 0");
                    textview2.setText("0");
                    isuser=true;
                    utotal=0;
                    utscore=0;
                    ctotal=0;
                    ctscore=0;
                }else {

                    isuser = false;
                    textview2.setText("0");
                    utotal = utotal + utscore;
                    textview.setText("Player Score: " + utotal + " Computer Score " + ctotal);
                    utscore = 0;
                    ctscore = 0;
                    Toast.makeText(MainActivity.this, "Computer Turn", Toast.LENGTH_LONG).show();
h.postDelayed(r,1000);
                    //computer();
                }
            }});
reset.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        textview.setText("Player Score: 0 Computer Score: 0");
        textview2.setText("0");
        isuser=true;
        utotal=0;
        utscore=0;
        ctotal=0;
        ctscore=0;
    }
});
    }
    final Handler h=new Handler();
    final Runnable r =new Runnable(){
        @Override
                public void run()
        {
            if(!isuser)
            {
                computer();
                h.postDelayed(this,1000);
            }
        }
    };
    public void computer()
    {

        Random rand=new Random();
        int[] res = {R.drawable.dice1, R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};

    if(ctscore<15) {

        int rndInt = rand.nextInt(res.length) + 1;
        img.setImageDrawable(getResources().getDrawable(res[rndInt - 1]));
        if (rndInt == 1) {
            //      textview.setText("Player score: "+utotal+" Computer Score: " + ctotal);

                    textview2.setText("0");
            ctscore = 0;
            //      isuser=true;
            Toast.makeText(MainActivity.this, "Player Turn", Toast.LENGTH_LONG).show();

            isuser=true;

        } else {

            ctscore = ctscore + rndInt;

            textview2.setText("" + ctscore);

        }
    }
    else
    {

        isuser=true;
        textview2.setText("0");
        ctotal=ctotal+ctscore;
        Toast.makeText(MainActivity.this,"Player Turn",Toast.LENGTH_SHORT).show();
        textview.setText("Person Score: " + utotal + "Computer Score: " + ctotal);
        ctscore = 0;
    }

    if(ctotal+ctscore>=100)
    {
     Toast.makeText(MainActivity.this,"Computer Wins",Toast.LENGTH_SHORT).show();
        textview.setText("Player Score: 0 Computer Score: 0");

        textview2.setText("0");
        isuser=true;
        utotal=0;
        utscore=0;
        ctotal=0;
        ctscore=0;
    }

    }

}
