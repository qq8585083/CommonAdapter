package com.hy.commonadapter.interfaces;


import com.hy.commonadapter.BaseAdapterHelper;

/**
 * 项目名称: CommonAdapter
 * 包 名 称: com.hy.commonadapter
 * 类 描 述: 扩展的Adapter接口规范
 * 创 建 人: qq8585083
 * 创建时间: 2016/1/21 17:54.
 */
public interface IAdapter<T> {

    void onUpdate(BaseAdapterHelper helper, T item, int position);

    int getLayoutResId(T item, int position);
}
