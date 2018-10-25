package com.example.honey.mytry;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.honey.mytry.entity.Answer;
import com.example.honey.mytry.entity.Page;
import com.example.honey.mytry.entity.Quesition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LayoutInflater xInflater;
    private Page page;

    private LinearLayout test_layout;
    private Page the_page;

    private ArrayList<Answer> the_answer_list;//答案列表
    private ArrayList<Quesition> the_quesition_list;//问题列表
    private View que_view;//问题所在的View
    private View ans_view;//答案所在的View
    //下面这两个list是为了实现点击的时候改变图片，因为单选多选时情况不一样，为了方便控制
    //存每个问题下的imageview
    private ArrayList<ArrayList<ImageView>> imglist=new ArrayList<ArrayList<ImageView>>();
    //存每个答案的imageview
    private ArrayList<ImageView> imglist2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);//初始化inflater

        //假数据
        initDate();//方法在本java

        //提交按钮
        Button button=(Button)findViewById(R.id.submit);//在xml中找到按钮
        button.setOnClickListener(new submitOnClickListener(page));//为按钮绑定事件
    }

    private void initDate() {
        //假数据、
//定义初始化答案
        Answer a_one=new Answer();
        a_one.setAnswerId("0");
        a_one.setAnswer_content("男");
        a_one.setAns_state(0);
        Answer a_two=new Answer();
        a_two.setAnswerId("1");
        a_two.setAnswer_content("女");
        a_two.setAns_state(0);

        Answer a_three=new Answer();
        a_three.setAnswerId("3");
        a_three.setAnswer_content("是");
        a_three.setAns_state(0);
        Answer a_four=new Answer();
        a_four.setAnswerId("4");
        a_four.setAnswer_content("不是");
        a_four.setAns_state(0);

        Answer a_three1=new Answer();
        a_three1.setAnswerId("3");
        a_three1.setAnswer_content("是");
        a_three1.setAns_state(0);
        Answer a_four1=new Answer();
        a_four1.setAnswerId("4");
        a_four1.setAnswer_content("不是");
        a_four1.setAns_state(0);

        ArrayList<Answer> answers_one=new ArrayList<Answer>();
        answers_one.add(a_one);
        answers_one.add(a_two);


        ArrayList<Answer> answers_two=new ArrayList<Answer>();
        answers_two.add(a_one);
        answers_two.add(a_two);
        answers_two.add(a_three);
        answers_two.add(a_four);

        ArrayList<Answer> answers_three=new ArrayList<Answer>();
        answers_three.add(a_one);
        answers_three.add(a_two);
        answers_three.add(a_three);
        answers_three.add(a_four);
        answers_three.add(a_three1);
        answers_three.add(a_four1);
//定义初始化问题

        Quesition q_one=new Quesition();
        q_one.setQuesitionId("00");
        q_one.setType("0");
        q_one.setContent("1、您的性别：");
        q_one.setAnswers(answers_one);
        q_one.setQue_state(0);

        Quesition q_two=new Quesition();
        q_two.setQuesitionId("01");
        q_two.setType("1");
        q_two.setContent("2、您是党员吗？");
        q_two.setAnswers(answers_two);
        q_two.setQue_state(0);

        Quesition q_three=new Quesition();
        q_three.setQuesitionId("03");
        q_three.setType("1");
        q_three.setContent("3、您是dsfsdfsd吗？");
        q_three.setAnswers(answers_three);
        q_three.setQue_state(0);

        ArrayList<Quesition> quesitions=new ArrayList<Quesition>();
        quesitions.add(q_one);
        quesitions.add(q_two);
        quesitions.add(q_three);
//初始化问卷（在全局中定义）
        page=new Page();
        page.setPageId("000");
        page.setStatus("0");
        page.setTitle("第一次调查问卷");
        page.setQuesitions(quesitions);
        //加载布局
         initView(page);
    }

    private void initView(Page page) {
        // TODO Auto-generated method stub
        //这是要把问题的动态布局加入的布局
        test_layout=(LinearLayout)findViewById(R.id.lly_test);//问题框
        TextView page_txt=(TextView)findViewById(R.id.txt_title);//测试标题框
        page_txt.setText(page.getTitle());//测试标题框设置文字内容为page的标题

        //获得问题即第二层的数据
        the_quesition_list=page.getQuesitions();//问题列表一个list
        //根据第二层问题的多少，来动态加载布局
        for(int i=0;i<the_quesition_list.size();i++){//list的长度来计算问题的数量

            que_view=xInflater.inflate(R.layout.quesition_layout, null);
            //这里是通过事先获得的布局文件来实例化具体控件，并且可以根据情况自定义控件 问题所在的View（que_view）


            TextView txt_que=(TextView)que_view.findViewById(R.id.txt_question_item);//实体化一个名叫txt_question_item的TextView

            //这是第三层布局要加入的地方
            LinearLayout add_layout=(LinearLayout)que_view.findViewById(R.id.lly_answer);//实体化lly_answer的LinearLayout

            //判断单选-多选来实现后面是*号还是*多选，
            if(the_quesition_list.get(i).getType().equals("1")){
                set(txt_que,the_quesition_list.get(i).getContent(),1);//将问题内容放入txt_question_item的TextView
                //set(textView,content,type)
            }else{
                set(txt_que,the_quesition_list.get(i).getContent(),0);
            }
            //获得答案即第三层数据
            the_answer_list=the_quesition_list.get(i).getAnswers();//获得本题目的答案选项
            imglist2=new ArrayList<ImageView>();
            for(int j=0;j<the_answer_list.size();j++){
                ans_view=xInflater.inflate(R.layout.answer_layout, null);//事先获得的布局文件answer_layout来实例化具体控件
                TextView txt_ans=(TextView)ans_view.findViewById(R.id.txt_answer_item);//问题选项文本框
                ImageView image=(ImageView)ans_view.findViewById(R.id.image);//问题选项图片
                View line_view=ans_view.findViewById(R.id.vw_line);//问题选项分割线
                if(j==the_answer_list.size()-1){
                    //最后一条答案下面不要线是指布局的问题
                    line_view.setVisibility(View.GONE);
                }
                //判断单选多选加载不同选项图片
                if(the_quesition_list.get(i).getType().equals("1")){//多选
                    image.setBackgroundResource(R.mipmap.first);

                }else{//单选
                    image.setBackgroundResource(R.mipmap.first);
                }
                Log.e("---", "------"+image);
                imglist2.add(image);//image的list加入image
                txt_ans.setText(the_answer_list.get(j).getAnswer_content());
                LinearLayout lly_answer_size=(LinearLayout)ans_view.findViewById(R.id.lly_answer_size);
                lly_answer_size.setOnClickListener(new answerItemOnClickListener(i,j,the_answer_list,txt_ans));
                add_layout.addView(ans_view);
            }
            imglist.add(imglist2);
            test_layout.addView(que_view);//加入问题所在view
        }
    }

    class submitOnClickListener implements View.OnClickListener {
        private Page page;
        public submitOnClickListener(Page page){
            this.page=page;
        }//onCreate提交page
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            //判断是否答完题
            boolean isState=true;
            //最终要的json数组
            JSONArray jsonArray = new JSONArray();
            //点击提交的时候，先判断状态，如果有未答完的就提示，如果没有再把每条答案提交（包含问卷ID 问题ID 及答案ID）
            //注：不用管是否是一个问题的答案，就以答案的个数为准来提交上述格式的数据
            for(int i=0;i<the_quesition_list.size();i++){
                the_answer_list=the_quesition_list.get(i).getAnswers();
                //判断是否有题没答完
                if(the_quesition_list.get(i).getQue_state()==0){
                    Toast.makeText(getApplicationContext(), "您第"+(i+1)+"题没有答完", Toast.LENGTH_LONG).show();
                    jsonArray=null;
                    isState=false;
                    break;
                }else{
                    for(int j=0;j<the_answer_list.size();j++){
                        if(the_answer_list.get(j).getAns_state()==1){
                            JSONObject json = new JSONObject();
                            try {
                                json.put("psychologicalId", page.getPageId());
                                json.put("questionId", the_quesition_list.get(i).getQuesitionId());
                                json.put("optionId", the_answer_list.get(j).getAnswerId());
                                jsonArray.put(json);
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            Intent intent =new Intent(MainActivity.this,Result.class);
                            // 启动
                            startActivity(intent);
                        }

                    }
                }

            }
            if(isState){
                if(jsonArray.length()>0){
                    Log.e("af", jsonArray.toString());
                    for(int item=0;item<jsonArray.length();item++){
                        JSONObject job;
                        try {
                            job = jsonArray.getJSONObject(item);
                            Log.e("----", "pageId--------"+job.get("pageId"));
                            Log.e("----", "quesitionId--------"+job.get("quesitionId"));
                            Log.e("----", "answerId--------"+job.get("answerId"));
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }  // 遍历 jsonarray 数组，把每一个对象转成 json 对象

                    }

                }

            }

        }
    }

    class answerItemOnClickListener implements View.OnClickListener {
        private int i;
        private int j;
        private TextView txt;
        private ArrayList<Answer> the_answer_lists;
        public answerItemOnClickListener(int i,int j, ArrayList<Answer> the_answer_list,TextView text){
            this.i=i;
            this.j=j;
            this.the_answer_lists=the_answer_list;
            this.txt=text;

        }
        //实现点击选项后改变选中状态以及对应图片
        @Override
        public void onClick(View arg0) {
            if(the_quesition_list.get(i).getType().equals("1")){
                //多选
                if(the_answer_lists.get(j).getAns_state()==0){
                    //如果未被选中
                    txt.setTextColor(Color.parseColor("#EA5514"));
                    imglist.get(i).get(j).setBackgroundResource(R.mipmap.dog);

                    the_answer_lists.get(j).setAns_state(1);
                    the_quesition_list.get(i).setQue_state(1);
                }else{
                    txt.setTextColor(Color.parseColor("#595757"));
                    imglist.get(i).get(j).setBackgroundResource(R.mipmap.first);//颜色图片改回去
                    the_answer_lists.get(j).setAns_state(0);
                    the_quesition_list.get(i).setQue_state(1);
                }
            }else{
                //单选

                for(int z=0;z<the_answer_lists.size();z++){
                    the_answer_lists.get(z).setAns_state(0);
                    imglist.get(i).get(z).setBackgroundResource(R.mipmap.first);//false
                }
                if(the_answer_lists.get(j).getAns_state()==0){
                    //如果当前未被选中
                    imglist.get(i).get(j).setBackgroundResource(R.mipmap.dog);//true
                    the_answer_lists.get(j).setAns_state(1);
                    the_quesition_list.get(i).setQue_state(1);
                }else{
                    //如果当前已被选中
                    the_answer_lists.get(j).setAns_state(1);
                    the_quesition_list.get(i).setQue_state(1);
                }

            }
            //判断当前选项是否选中



        }

    }

    private void set(TextView tv_test, String content,int type) {
        //为了加载问题后面的* 和*多选
        // TODO Auto-generated method stub
        String w;
        if(type==1){
            w = content+"*[多选题]";
        }else{
            w = content+"*";
        }

        int start = content.length();
        int end = w.length();
        Spannable word = new SpannableString(w);
        word.setSpan(new AbsoluteSizeSpan(25), start, end,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        word.setSpan(new StyleSpan(Typeface.BOLD), start, end,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        word.setSpan(new ForegroundColorSpan(Color.RED), start, end,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tv_test.setText(word);
    }

    public void Main2Res(View v) {
        // 给bnt1添加点击响应事件
        Intent intent =new Intent(MainActivity.this,Result.class);
        // 启动
        startActivity(intent);
    }






}