package com.example.honey.mytry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class testme extends AppCompatActivity {

    private ImageView testOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testme);

        testOne=findViewById(R.id.testone);
        testOne.setBackgroundResource(R.mipmap.first);
//        testOne.setImageDrawable(getResources().getDrawable(R.drawable.first));
    }
}
