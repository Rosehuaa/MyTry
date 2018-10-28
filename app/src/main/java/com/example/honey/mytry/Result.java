package com.example.honey.mytry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rorbin.q.radarview.RadarData;
import rorbin.q.radarview.RadarView;

public class Result extends AppCompatActivity {

    public RadarView re;
    private final String TAG = "ConfigActivity";
    private float sum,o,c,e,a,n;
    TextView ana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        Toast.makeText(getApplicationContext(), "进入雷达图", Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
//        Toast.makeText(getApplicationContext(), "加载布局", Toast.LENGTH_LONG).show();

        Intent intent = getIntent();
        o = intent.getFloatExtra("o",0.0f);
        c = intent.getFloatExtra("c",0.0f);
        e = intent.getFloatExtra("e",0.0f);
        a = intent.getFloatExtra("a",0.0f);
        n = intent.getFloatExtra("n",0.0f);
//        Toast.makeText(getApplicationContext(), "得到数据", Toast.LENGTH_LONG).show();

        System.out.printf("%f,%f,%f,%f,%f",o,c,e,a,n);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        sum=(o+c+e+a+n)/5;

        List<Float> values = new ArrayList<>();
        Collections.addAll(values, o, c, e, a, n);
        RadarData data = new RadarData(values);
        re=(RadarView) findViewById(R.id.radarView);
        re.addData(data);

        List<String> name = new ArrayList<>();
        Collections.addAll(name, "开放性", "责任心", "外倾性", "宜人性", "神经质");
        re.setVertexText(name);


    }
    public void Re2re(View v) {
        // 给bnt1添加点击响应事件
        Intent toresult =new Intent(Result.this,result2.class);
        toresult.putExtra("o", o);
        toresult.putExtra("c", c);
        toresult.putExtra("e", e);
        toresult.putExtra("a", a);
        toresult.putExtra("n", n);
        // 启动
        startActivity(toresult);
    }

}
