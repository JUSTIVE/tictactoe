package com.example.benkim.tictactoe;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    Window window;
    ActionBar ab;
    FloatingActionButton restart;
    TextView p1,p2,status;
    ImageButton[] areas;
    boolean isAI=false;
    int winner;
    int[] data= new int[9];
    int turn;
    int counter;
    boolean isPlaying;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        p1=(TextView)findViewById(R.id.act2_player1ID);
        p2=(TextView)findViewById(R.id.act2_player2ID);
        p1.setText(getIntent().getStringExtra("player1ID"));
        p2.setText(getIntent().getStringExtra("player2ID"));
        p1.setTextColor(Color.parseColor("#666666"));
        p2.setTextColor(Color.parseColor("#666666"));
        status=(TextView)findViewById(R.id.status);
        restart=(FloatingActionButton)findViewById(R.id.act2_refreshFAB);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (winner){
                    case -1:
                        tintSystemBarsGrayToOne();
                        break;
                    case 1:tintSystemBarsTwoToOne();
                        break;
                }

                init();
            }
        });
        isAI=getIntent().getBooleanExtra("isAI",false);
        ab=getSupportActionBar();
        ab.setTitle("Tic Tac Toe");
        ab.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getApplicationContext(),R.color.player1Color)));
        areas= new ImageButton[9];
        areas[0]=(ImageButton) findViewById(R.id.area1);
        areas[1]=(ImageButton)findViewById(R.id.area2);
        areas[2]=(ImageButton)findViewById(R.id.area3);
        areas[3]=(ImageButton)findViewById(R.id.area4);
        areas[4]=(ImageButton)findViewById(R.id.area5);
        areas[5]=(ImageButton)findViewById(R.id.area6);
        areas[6]=(ImageButton)findViewById(R.id.area7);
        areas[7]=(ImageButton)findViewById(R.id.area8);
        areas[8]=(ImageButton)findViewById(R.id.area9);
        window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.player1ColorDark));
        Log.d("isAI",isAI+"");
        init();
    }
    void init(){
        for(int i=0;i<9;i++) {
            areas[i].setBackgroundColor(Color.parseColor("#00000000"));
            areas[i].setImageDrawable(getDrawable(R.drawable.question));
            areas[i].setOnClickListener(this);
            data[i]=-1;
        }
        restart.setVisibility(View.INVISIBLE);
        status.setText("<");
        status.setTextColor(Color.parseColor("#00000000"));
        turn = 0;
        counter=0;
        winner=-1;
        isPlaying=true;



    }
    private void tintSystemBarsTwoToOne() {
        // Initial colors of each system bar.
        final int statusBarColor = ContextCompat.getColor(getApplicationContext(),R.color.player2Color);
        final int toolbarColor = ContextCompat.getColor(getApplicationContext(),R.color.player2ColorDark);

        // Desired final colors of each bar.
        final int statusBarToColor = ContextCompat.getColor(getApplicationContext(),R.color.player1Color);
        final int toolbarToColor = ContextCompat.getColor(getApplicationContext(),R.color.player1ColorDark);

        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // Use animation position to blend colors.
                float position = animation.getAnimatedFraction();

                // Apply blended color to the status bar.
                int blended = blendColors(statusBarColor, statusBarToColor, position);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(blended);
                }

                // Apply blended color to the ActionBar.
                blended = blendColors(toolbarColor, toolbarToColor, position);
                ColorDrawable background = new ColorDrawable(blended);
                getSupportActionBar().setBackgroundDrawable(background);
            }
        });

        anim.setDuration(500).start();
    }
    private void tintSystemBarsOneToTwo() {
        // Initial colors of each system bar.
        final int statusBarColor = ContextCompat.getColor(getApplicationContext(),R.color.player1Color);
        final int toolbarColor = ContextCompat.getColor(getApplicationContext(),R.color.player1ColorDark);

        // Desired final colors of each bar.
        final int statusBarToColor = ContextCompat.getColor(getApplicationContext(),R.color.player2Color);
        final int toolbarToColor = ContextCompat.getColor(getApplicationContext(),R.color.player2ColorDark);

        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // Use animation position to blend colors.
                float position = animation.getAnimatedFraction();

                // Apply blended color to the status bar.
                int blended = blendColors(statusBarColor, statusBarToColor, position);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(blended);
                }

                // Apply blended color to the ActionBar.
                blended = blendColors(toolbarColor, toolbarToColor, position);
                ColorDrawable background = new ColorDrawable(blended);
                getSupportActionBar().setBackgroundDrawable(background);
            }
        });

        anim.setDuration(500).start();
    }
    private void tintSystemBarsGrayToOne() {
        // Initial colors of each system bar.
        final int statusBarColor = ContextCompat.getColor(getApplicationContext(),R.color.grayPrimary);
        final int toolbarColor = ContextCompat.getColor(getApplicationContext(),R.color.grayPrimaryDark);

        // Desired final colors of each bar.
        final int statusBarToColor = ContextCompat.getColor(getApplicationContext(),R.color.player1Color);
        final int toolbarToColor = ContextCompat.getColor(getApplicationContext(),R.color.player1ColorDark);

        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // Use animation position to blend colors.
                float position = animation.getAnimatedFraction();

                // Apply blended color to the status bar.
                int blended = blendColors(statusBarColor, statusBarToColor, position);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(blended);
                }

                // Apply blended color to the ActionBar.
                blended = blendColors(toolbarColor, toolbarToColor, position);
                ColorDrawable background = new ColorDrawable(blended);
                getSupportActionBar().setBackgroundDrawable(background);
            }
        });

        anim.setDuration(500).start();
    }
    private void tintSystemBarsOneToGray() {
        // Initial colors of each system bar.
        final int statusBarColor = ContextCompat.getColor(getApplicationContext(),R.color.player1Color);
        final int toolbarColor = ContextCompat.getColor(getApplicationContext(),R.color.player1ColorDark);

        // Desired final colors of each bar.
        final int statusBarToColor = ContextCompat.getColor(getApplicationContext(),R.color.grayPrimary);
        final int toolbarToColor = ContextCompat.getColor(getApplicationContext(),R.color.grayPrimaryDark);

        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // Use animation position to blend colors.
                float position = animation.getAnimatedFraction();

                // Apply blended color to the status bar.
                int blended = blendColors(statusBarColor, statusBarToColor, position);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(blended);
                }

                // Apply blended color to the ActionBar.
                blended = blendColors(toolbarColor, toolbarToColor, position);
                ColorDrawable background = new ColorDrawable(blended);
                getSupportActionBar().setBackgroundDrawable(background);
            }
        });

        anim.setDuration(500).start();
    }
    private int blendColors(int from, int to, float ratio) {
        final float inverseRatio = 1f - ratio;

        final float r = Color.red(to) * ratio + Color.red(from) * inverseRatio;
        final float g = Color.green(to) * ratio + Color.green(from) * inverseRatio;
        final float b = Color.blue(to) * ratio + Color.blue(from) * inverseRatio;

        return Color.rgb((int) r, (int) g, (int) b);
    }

    @Override
    public void onClick(View v) {
        if(isPlaying) {
            String id=v.getResources().getResourceName(v.getId());
            id= id.charAt(id.length()-1)+"";

            int index = Integer.parseInt(id)-1;
            Log.d("wtf",id);
            if(data[index]==-1) {
                Log.d("turn",turn+"");

                if (turn == 0) {
                    v.setBackgroundColor(Color.parseColor("#00000000"));
                    ((ImageButton) v).setImageDrawable(getDrawable( R.drawable.circle_icon));
                    data[index] = turn;
                    counter++;
                    if(isAI){
                        int result=checkAll();
                        if (result >-1){
                            if(result==0) {
                                status.setTextColor(ContextCompat.getColorStateList(getApplicationContext(),R.color.player1Color));
                                tintSystemBarsTwoToOne();
                                tintSystemBarsOneToTwo();
                                tintSystemBarsTwoToOne();
                                status.setText(p1.getText().toString() + " wins!");
                                winner=0;
                            }
                            else {

                                tintSystemBarsOneToTwo();
                                tintSystemBarsTwoToOne();
                                tintSystemBarsOneToTwo();
                                status.setTextColor(ContextCompat.getColorStateList(getApplicationContext(),R.color.player2Color));
                                winner=1;
                                status.setText(p2.getText().toString() + " wins!");
                            }
                            restart.setVisibility(View.VISIBLE);
                            isPlaying=false;
                            return;
                        }
                        aiPlaying();
                    }
                    else{
                        tintSystemBarsOneToTwo();
                    }
                    //window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.player2ColorDark));

                } else {


                    ((ImageButton) v).setImageDrawable(getDrawable(R.drawable.ic_close));
                    v.setBackgroundColor(Color.parseColor("#00000000"));
                    //ab.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getApplicationContext(),R.color.player1Color)));
                    tintSystemBarsTwoToOne();
                    //window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.player1ColorDark));
                    data[index] = turn;
                    counter++;
                }


                if(!isAI) {

                    turn += 1;
                    turn = turn % 2;

                }

                Log.d("count",counter+"");
            }


            int result=checkAll();
            if (result >-1){
                if(result==0) {
                    status.setTextColor(ContextCompat.getColorStateList(getApplicationContext(),R.color.player1Color));
                    tintSystemBarsTwoToOne();
                    tintSystemBarsOneToTwo();
                    tintSystemBarsTwoToOne();
                    status.setText(p1.getText().toString() + " wins!");
                    winner=0;
                }
                else {
                    tintSystemBarsOneToTwo();
                    tintSystemBarsTwoToOne();
                    tintSystemBarsOneToTwo();
                    status.setTextColor(ContextCompat.getColorStateList(getApplicationContext(),R.color.player2Color));
                    winner=1;
                    status.setText(p2.getText().toString() + " wins!");
                }
                restart.setVisibility(View.VISIBLE);
                isPlaying=false;
                return;
            }
            if(counter==9){//비긴 경우
                status.setTextColor(Color.parseColor("#666666"));
                tintSystemBarsOneToGray();
                winner=-1;
                status.setText("DRAW!");
                restart.setVisibility(View.VISIBLE);
                isPlaying=false;
                return;
            }
        }
    }
    // 0 is player 1 win, 1 is player 2 win, -1 is none
    int checkAll(){
        //search rows
        int result=-1;
        for(int i=0;i<3;i++){
            if(data[i*3]==data[(i*3)+1]&&data[i*3]==data[(i*3)+2]){
                result=data[i*3];
                if(result!=-1)
                    return result;
            }
        }
        for(int i=0;i<3;i++){
            if(data[i]==data[(i+3)]&&data[i]==data[(i+6)]){
                result=data[i];
                if(result!=-1)
                    return result;
            }
        }
        //cross

        if((data[0]==data[4]&&data[0]==data[8])||(data[2]==data[4]&&data[2]==data[6])){
            result=data[4];
            return result;
        }

        return result;
    }
    void aiPlaying(){
        if(counter==9)
            return;
        int target = aiThinking();

        data[target]=1;
        areas[target].setImageDrawable(getDrawable( R.drawable.ic_close));
        //areas[target].setBackgroundColor(Color.parseColor("#00000000"));

        //turn += 1;
        //turn = turn % 2;
        counter++;
    }
    int aiThinking(){//data[i]가 -1이 아님을 보장
        int ai_case =0;
        int guess=-1;
        if(data[4]==-1){//중앙이 비면 중앙을 먼저 먹음
            guess=4;
            return guess;
        }
        //i'll gonna check any two connected ones
        //vertical-check
        for(int i=0;i<2;i++){
            for(int j=0;j<3;j++){
                if((data[(i*3)+j]==data[(i*3)+j+3])&&(data[(i*3)+j]!=-1)&&data[((i*3)+j+6)%9]==-1){
                    guess=((i*3)+j+6)%9;
                    ai_case=0;
                }
            }
        }
        //horizontal-check
        for(int i=0;i<2;i++){
            for(int j=0;j<3;j++){
                if((data[i+(j*3)]==data[i+(j*3)+1])&&(data[i+(j*3)]!=-1)&&data[(3*j)+2-(2*i)]==-1){
                    ai_case=1;
                    guess=(3*j)+2-(2*i);
                }
            }
        }
        //vertical check - 101 case
        for(int i=0;i<3;i++){
            if((data[i]==data[i+6])&&(data[i]!=-1)&&(data[i+3]==-1)){
                ai_case=2;
                guess = i+3;
            }
        }
        //horizontal check - 101 case
        for(int i=0;i<3;i++){
            if((data[3*i]==data[3*i+2])&&(data[3*i]!=-1)&&(data[i*3+1]==-1)){
                ai_case=3;
                guess = i*3+1;
            }
        }
        //check cross
        for(int i=0;i<2;i++){
            if((data[4*(i+1)]==data[4*i])&&(data[4*i]!=-1)&&(data[8*(1-i)]==-1)){
                ai_case=4;
                guess = 8*(1-i);
            }
        }
        for(int i=0;i<2;i++){
            if((data[2*(1+i)]==data[2*(2+i)])&&(data[2*(1+i)]!=-1)&&(data[6-(4*i)]==-1)){
                ai_case=5;
                guess = 6-(4*i);
            }
        }
        if(counter==3){
            if((data[0]==data[8])||(data[2]==data[6])){
                ai_case=6;
                guess=(((int)(Math.random()*8))%2)+1;
            }
        }


        Log.d("ai_case",ai_case+"");
        Log.d("guess is ", guess+"");
        if(guess==-1) {
            for (int i = 0; i < 9; i++) {
                if (data[i] == -1)
                    guess = i;
            }
        }
        return guess;
    }
}
