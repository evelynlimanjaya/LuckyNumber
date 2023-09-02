package com.elimanjaya.luckynumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class ResultActivity extends AppCompatActivity {
    TextView welcomeTxt, luckyNumberTxt;
    Button shareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        welcomeTxt = findViewById(R.id.textView2);
        luckyNumberTxt = findViewById(R.id.luckyNumber);
        shareBtn = findViewById(R.id.shareButton);

        Intent i = getIntent();
        String name = i.getStringExtra("name");

        int randomNum = generateRandomNumber();
        luckyNumberTxt.setText(Integer.toString(randomNum));

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareData(name, randomNum);
            }
        });
    }

    public int generateRandomNumber(){
        Random r = new Random();
        int upperLimit = 1000;
        int randomNumber = r.nextInt(upperLimit);

        return  randomNumber;
    }

    private void shareData(String username, int num){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        i.putExtra(Intent.EXTRA_SUBJECT, username + " got Lucky today!");
        i.putExtra(Intent.EXTRA_TEXT, "His lucky number is " + num);

        startActivity(Intent.createChooser(i, "Choose a Platform"));
    }
}