package com.example.asus.yklx2.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.yklx2.R;

/**
 * Created by asus on 2018/5/29.
 */

public class MyTitle extends LinearLayout {

    private ImageView img;
    private TextView tvv;
    private EditText et;
    private  onsetHuida onsethuida;
    public MyTitle(Context context) {
        this(context,null);
    }

    public MyTitle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTitle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater from = LayoutInflater.from(context);
        View inflate = from.inflate(R.layout.title, this, true);
        img = (ImageView)inflate.findViewById(R.id.back);
        tvv = inflate.findViewById(R.id.sousuo);
        et = (EditText)inflate.findViewById(R.id.et);
        tvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onsethuida.huida(et.getText().toString());
            }
        });

    }
    public interface onsetHuida{
        void huida(String aa);
    }
    public void setJiekou(onsetHuida onsethuida){
        this.onsethuida=onsethuida;
    }

}
