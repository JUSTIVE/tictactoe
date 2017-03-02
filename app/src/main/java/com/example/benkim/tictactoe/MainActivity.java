package com.example.benkim.tictactoe;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    TextInputLayout t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (TextInputLayout) findViewById(R.id.textInputLayout3);
        t2 = (TextInputLayout)findViewById(R.id.textInputLayout2);
        fab=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                if(!t1.getEditText().getText().toString().equals(""))
                    intent.putExtra("player1ID",t1.getEditText().getText().toString());
                else
                    intent.putExtra("player1ID","player1");
                if(!t2.getEditText().getText().toString().equals("")) {
                    intent.putExtra("player2ID", t2.getEditText().getText().toString());
                    intent.putExtra("isAI",false);
                }
                else {
                    intent.putExtra("player2ID", "Computer");
                    intent.putExtra("isAI", true);
                }
                    //intent.putExtra("player2ID","player2");

                startActivity(intent);
            }
        });
    }
}
