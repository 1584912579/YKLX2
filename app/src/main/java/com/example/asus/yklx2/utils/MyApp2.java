package com.example.asus.yklx2.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by asus on 2018/5/29.
 */

public class MyApp2 extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Uri uri = Uri.parse((String) path);
        imageView.setImageURI(uri);
    }
    @Override
    public ImageView createImageView(Context context){
        SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
        return simpleDraweeView;
    }
}
