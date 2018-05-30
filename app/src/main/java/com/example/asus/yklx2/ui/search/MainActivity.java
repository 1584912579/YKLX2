package com.example.asus.yklx2.ui.search;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.yklx2.R;
import com.example.asus.yklx2.ui.search.adapter.myBaseAdapter;
import com.example.asus.yklx2.utils.FlowLayout;
import com.example.asus.yklx2.utils.MyTitle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String mNames[] = {
            "平板","电脑","手机"
    };
    private List<String> list=new ArrayList<>();
    private MyTitle mTit;
    private FlowLayout mFlo;
    private ListView mLv;
    private TextView bt;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        initChildViews();
        jilu();
    }

    private void initView() {
        mTit = (MyTitle) findViewById(R.id.tit);
        mFlo = (FlowLayout) findViewById(R.id.flo);
        mLv = (ListView) findViewById(R.id.lv);
        bt = (TextView) findViewById(R.id.sousuo);
        et = (EditText) findViewById(R.id.et);
    }
    private void initChildViews() {
        ViewGroup.MarginLayoutParams ip = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ip.leftMargin=5;
        ip.rightMargin = 5;
        ip.topMargin = 5;
        ip.bottomMargin = 5;
        for( int i = 0; i < mNames.length; i ++){
            TextView view = new TextView(this);
            view.setText(mNames[i]);
            view.setTextColor(Color.BLACK);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.line2));
            mFlo.addView(view,ip);
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    intent.putExtra("keywords",mNames[finalI]);
                    startActivity(intent);
                    list.add(mNames[finalI]);
                    mLv.setAdapter(new myBaseAdapter(MainActivity.this,list));


                }
            });


        }
    }
    private void jilu() {
        mTit.setJiekou(new MyTitle.onsetHuida() {
            @Override
            public void huida(String aa) {
               // Toast.makeText(MainActivity.this, aa+"", Toast.LENGTH_SHORT).show();
                list.add(aa);
                mLv.setAdapter(new myBaseAdapter(MainActivity.this,list));
                if (!TextUtils.isEmpty(et.getText().toString())) {


                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    intent.putExtra("keywords", et.getText().toString());
                    startActivity(intent);
                }
            }
        });

        mLv.setAdapter(new myBaseAdapter(MainActivity.this,list));
    }
}
