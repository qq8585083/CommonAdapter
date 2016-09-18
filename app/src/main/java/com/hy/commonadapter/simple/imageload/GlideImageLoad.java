package com.hy.commonadapter.simple.imageload;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hy.commonadapter.interfaces.ImageLoad;

public class GlideImageLoad implements ImageLoad {

    @Override public void load(Context context, ImageView imageView, String imageUrl) {
        if(TextUtils.isEmpty(imageUrl)) return;
        Glide.with(context).load(imageUrl).centerCrop().crossFade().into(imageView);
    }
}
