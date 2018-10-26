package com.example.honey.mytry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rorbin.q.radarview.RadarData;
import rorbin.q.radarview.RadarView;

public class Result extends AppCompatActivity {

    public RadarView re;
    private final String TAG = "ConfigActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        float o = intent.getFloatExtra("o",0.0f);
        float c = intent.getFloatExtra("c",0.0f);
        float e = intent.getFloatExtra("e",0.0f);
        float a = intent.getFloatExtra("a",0.0f);
        float n = intent.getFloatExtra("n",0.0f);

        Log.i(TAG, "o: dollar2=" + o);
        Log.i(TAG, "c: euro2=" + c);
        Log.i(TAG, "e: won2=" + e);




        List<Float> values = new ArrayList<>();
        Collections.addAll(values, o, c, e, a, n);
        RadarData data = new RadarData(values);
        re=(RadarView) findViewById(R.id.radarView);
        re.addData(data);

        List<String> name = new ArrayList<>();
        Collections.addAll(name, "开放性", "责任心", "外倾性", "宜人性", "神经质");
        re.setVertexText(name);
    }
}
