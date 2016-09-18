package com.hy.commonadapter.interfaces;

import android.content.Context;
import android.widget.ImageView;

/**
 * 项目名称: CommonAdapter
 * 包 名 称: com.hy.commonadapter
 * 类 描 述: 网络图片加载接口规范
 * 创 建 人: qq8585083
 * 创建时间: 2016/1/21 17:54.
 */
public interface ImageLoad {

    void load(Context context, ImageView imageView, String imageUrl);
}
