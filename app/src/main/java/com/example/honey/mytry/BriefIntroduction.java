package com.example.honey.mytry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.honey.mytry.entity.Question;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class BriefIntroduction extends AppCompatActivity {

    private TextView content;
    private String name;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brief_introduction);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        System.out.printf("%s",name);
        System.out.println("~~~~~~~~~~~~~~");
        path=name+".txt";
        System.out.printf("%s",path);
        System.out.println("~~~~~~~~~~~~~~");


        content=(TextView)findViewById(R.id.content);

        try {
            content.setText(getBrief());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void Bri2Main2(View v) {


        if(name.equals("ocean")){
            // 给bnt1添加点击响应事件
            Intent intent =new Intent(BriefIntroduction.this,MainActivity.class);

            intent.putExtra("path", "ocean_q2.txt");
            // 启动
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "未找到测试题", Toast.LENGTH_LONG).show();
        }

    }
    public void Bri2Main(View v){
        // 给bnt1添加点击响应事件
        Intent intent =new Intent(BriefIntroduction.this,MainActivity.class);

        intent.putExtra("path", "ocean_q.txt");
        // 启动
        startActivity(intent);

    }

    public String getBrief() throws IOException {

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.printf("%s",path);
        String content;
        InputStream fileInputStream = null;
        Scanner in;
        fileInputStream = getAssets().open(path);
         //一定要声明为GBK编码,因为默认编码为GBK
        in = new Scanner(fileInputStream, "GBK");
        content = in.nextLine();
        return content;
    }

}
