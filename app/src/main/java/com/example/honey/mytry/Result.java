package com.example.honey.mytry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rorbin.q.radarview.RadarData;
import rorbin.q.radarview.RadarView;

public class Result extends AppCompatActivity {

    public RadarView re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);




        List<Float> values = new ArrayList<>();
        Collections.addAll(values, 3.2f, 6.7f, 2f, 7f, 5.6f);
        RadarData data = new RadarData(values);
        re=(RadarView) findViewById(R.id.radarView);
        re.addData(data);

        List<String> name = new ArrayList<>();
        Collections.addAll(name, "开放性", "责任心", "外倾性", "宜人性", "神经质");
        re.setVertexText(name);
    }
}
