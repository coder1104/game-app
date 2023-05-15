package com.example.tap_gameapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tap_gameapp.dialogs.PauseDialog;
import com.example.tap_gameapp.util.ActionListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private View upr_first, upr_second, lwr_first, lwr_second;
    private TextView tv_score, tv_time, tv_startingTimer;
    int currentGeneratedNo, points = 0, seconds = 0, minutes = 0, counter;
    NumberFormat f = new DecimalFormat("00");
    int startingTime;
    boolean gameOver = false, is_mltpl_click = false, is_click = false,isReplay=false;
    boolean hasBeenPaused = false,isGameStart=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        if(savedInstanceState!=null){
            minutes=savedInstanceState.getInt("mint");
            seconds=savedInstanceState.getInt("sec");
            points=savedInstanceState.getInt("point");
            counter=savedInstanceState.getInt("time");
            hasBeenPaused=savedInstanceState.getBoolean("pause");
            isGameStart=savedInstanceState.getBoolean("start");
           // onResume();
        }
        Button btn_start = findViewById(R.id.btn_start);
        upr_first = findViewById(R.id.box1);
        upr_second = findViewById(R.id.box2);
        lwr_first = findViewById(R.id.box3);
        lwr_second = findViewById(R.id.box4);
        tv_score = findViewById(R.id.score);
        tv_time = findViewById(R.id.timer);
        tv_startingTimer = findViewById(R.id.startingtime);
        String str_time = (" Time \n  " + f.format(minutes) + ":" + f.format(seconds));
        tv_time.setText(str_time);
        String str_score = (" Score \n   " + String.valueOf(points));
        tv_score.setText(str_score);
        if(!isGameStart){
        btn_start.setOnClickListener(view -> {
            startingTimer();
        });}
    }

    private void startingTimer() {

        startingTime = 3;
        if(!isGameStart){
            isGameStart = true;
        new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {
                if(startingTime ==0){
                    tv_startingTimer.setText("Go!");
                }
                else
                    tv_startingTimer.setText(String.valueOf(startingTime));
                startingTime--;
            }

            public void onFinish() {
                game();
                //tv_startingTimer.setText("Go!");
                attachListeners();

            }
        }.start();}

    }


    @Override
    protected void onPause() {
        super.onPause();
        hasBeenPaused = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (hasBeenPaused ) {
            hasBeenPaused = false;
        }
        if(isGameStart ){
            //startingTimer();
            game();

       }
    }


    private void game() {

        int random = getRandomNumber();
        if (!hasBeenPaused) {
            new CountDownTimer(1000, 1000) {

                public void onTick(long millisUntilFinished) {

                }

                public void onFinish() {
                    if (hasBeenPaused)
                        return;
                    String str_time = (" Time \n  " + f.format(minutes) + ":" + f.format(seconds));
                    tv_time.setText(str_time);
                    ++counter;


                    if (!gameOver && is_click ) {
                        upr_first.setBackgroundResource(R.color.red);
                        upr_second.setBackgroundResource(R.color.blue);
                        lwr_first.setBackgroundResource(R.color.green);
                        lwr_second.setBackgroundResource(R.color.yellow);
                        seconds++;
                        if (seconds == 60) {
                            seconds = 0;
                        }
                        minutes = counter / 60;

                        switch (random) {
                            case 0:
                                upr_first.setBackgroundResource(R.color.black);
                                break;
                            case 1:
                                upr_second.setBackgroundResource(R.color.black);
                                break;
                            case 2:
                                lwr_first.setBackgroundResource(R.color.black);
                                break;
                            case 3:
                                lwr_second.setBackgroundResource(R.color.black);
                                break;

                        }
                        currentGeneratedNo = random;
                        is_click = false;
                        game();
                    } else {
                        if (!hasBeenPaused)
                            dialogGameOver();

                    }
                    // tv_startingTimer.setVisibility(View.GONE);
                    tv_startingTimer.setText("");
                    is_mltpl_click = false;
                }
            }.start();
        }

    }

    private void attachListeners() {
        is_click = true;
        upr_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentGeneratedNo == 0) {
                    if (!is_mltpl_click) {
                        ++points;
                        is_mltpl_click = true;
                    }
                    String str_score = ("Score \n   " + String.valueOf(points));
                    tv_score.setText(str_score);
                } else {
                    gameOver = true;
                }
                is_click = true;

            }
        });
        upr_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentGeneratedNo == 1) {
                    if (!is_mltpl_click) {
                        ++points;
                        is_mltpl_click = true;
                    }
                    String str_score = ("Score \n   " + String.valueOf(points));
                    tv_score.setText(str_score);
                } else {
                    gameOver = true;
                }
                is_click = true;
            }
        });
        lwr_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentGeneratedNo == 2) {
                    if (!is_mltpl_click) {
                        ++points;
                        is_mltpl_click = true;
                    }
                    String str_score = ("Score \n  " + String.valueOf(points));
                    tv_score.setText(str_score);
                } else {
                    gameOver = true;
                }
                is_click = true;
            }

        });
        lwr_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentGeneratedNo == 3) {
                    if (!is_mltpl_click) {
                        ++points;
                        is_mltpl_click = true;
                    }
                    String str_score = ("Score \n   " + String.valueOf(points));
                    tv_score.setText(str_score);
                } else {
                    gameOver = true;
                }
                is_click = true;
            }
        });

    }

    private int getRandomNumber() {
        Random random = new Random();
        int n = currentGeneratedNo;
        while (n == currentGeneratedNo) {
            n = random.nextInt(4);
        }
        return n;
    }

    private void dialogGameOver() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.custom_dilogue, null);
        builder.setView(customLayout);
        TextView dl_score = customLayout.findViewById(R.id.dl_score);
        String str_pnt = ("You have Score :" + String.valueOf(points));
        dl_score.setText(str_pnt);

        TextView dl_time = customLayout.findViewById(R.id.dl_time);
        String str_time = String.valueOf("Your time is:"  + f.format(minutes) + ":" + f.format(seconds));
        dl_time.setText(str_time);

        Button dl_btn = customLayout.findViewById(R.id.dl_replay);
        dl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
//                    game();
               // isReplay=true;

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void onBackPressed() {
        onPause();

        PauseDialog pauseDialog = new PauseDialog(this, new ActionListener() {
            @Override
            public void onPositiveAction() {
                finish();
            }

            @Override
            public void onNegativeAction() {
               if(!gameOver) {
                   onResume();
               }
               else
                   return;


            }
        });
        pauseDialog.show();


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("mint",minutes);
        outState.putInt("sec",seconds);
        outState.putInt("point",points);
        outState.putInt("time",counter);
        outState.putBoolean("pause",hasBeenPaused);
        outState.putBoolean("start",isGameStart);
        super.onSaveInstanceState(outState);
    }


}