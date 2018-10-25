package com.example.honey.mytry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BriefIntroduction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brief_introduction);
    }

    public void Bri2Main(View v) {
        // 给bnt1添加点击响应事件
        Intent intent =new Intent(BriefIntroduction.this,MainActivity.class);
        // 启动
        startActivity(intent);
    }

}
