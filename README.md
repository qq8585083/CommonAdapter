[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-CommonAdapter-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/3727)

- 简化大量重复代码
- 支持多布局
- 自定义图片加载
- 常用数据操作
- view复用
- RecyclerView item 点击和长按事件


##图片展示
![image](https://raw.githubusercontent.com/qq8585083/CommonAdapter/master/CommonAdapter.gif)


##gradle依赖
```gradle
repositories {
    maven {
        url 'https://dl.bintray.com/qq8585083/maven/'
    }
}
....
dependencies {
    compile 'com.hy.commonadapter:commonadapter:1.0.0'
     //项目中使用到RecyclerView,需要添加依赖
    compile 'com.android.support:recyclerview-v7:23.4.0'
```

##开始使用
ListView/GridView 使用示例
```java
List<News> newsList = ...;
//单布局文件
listView = (ListView) findViewById(R.id.listview);
listView.setAdapter(new CommonAdapter<News>(context,
    //item布局文件
    R.layout.item_none_picture, newsList ) {
    @Override public void onUpdate(BaseAdapterHelper helper, News item, int position) {
        //BaseAdapterHelper详细用法，见下方

        helper.setText(R.id.xxx, item.getTitle())
               //可实现ImageLoad接口，进行图片自定义加载方式，demo里面使用的Glide
              .setImageLoad(new GlideImageLoad())
              .setImageUrl(R.id.xxx,item.getCoverUrl());
    }
});

//多布局文件
private final class MultipleLayoutAdapter extends CommonAdapter<News>{

    public MultipleLayoutAdapter(Context context, int layoutResId, List<News> data) {
        super(context, layoutResId, data);
    }
    //多种布局重写此方法即可
    @Override public int getLayoutResId(News item, int position) {
        int layoutResId = -1;
        switch (item.getNewsType()){
            case News.TYPE_NONE_PICTURE: //布局样式一
                layoutResId = R.layout.item_none_picture;
                break;
            case News.TYPE_SINGLE_PICTURE: //布局样式二
                layoutResId = R.layout.item_single_picture;
                break;
            case News.TYPE_MULTIPLE_PICTURE: //布局样式三
                layoutResId = R.layout.item_multiple_picture;
                break;

            更多的布局样式 ...
        }
        return layoutResId;
    }

    @Override public void onUpdate(BaseAdapterHelper helper, News item, int position) {
        helper.setImageLoad(new GlideImageLoad());
        switch (item.getNewsType()){
            case News.TYPE_NONE_PICTURE: //布局样式一
                helper.setText(R.id.xxx, item.getTitle())
                      .setImageUrl(R.id.xxx,item.getCoverUrl());
                break;
            case News.TYPE_SINGLE_PICTURE: //布局样式二
                helper.setText(R.id.xxx, item.getTitle())
                      .setImageUrl(R.id.xxx,item.getCoverUrl());
                break;
            case News.TYPE_MULTIPLE_PICTURE: //布局样式三
                helper.setText(R.id.xxx, item.getTitle())
                      .setImageUrl(R.id.xxx,item.getCoverUrl());
                break;

            更多的布局样式 ...
        }
    }
}
```
RecyclerView 使用示例
```java
List<News> newsList = ...;
//单布局文件
recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
recyclerView.setHasFixedSize(true);
recyclerView.setItemAnimator(new DefaultItemAnimator());
newsAdapter = new NewsAdapter(this, R.layout.item_none_picture, newsList);
recyclerView.setAdapter(newsAdapter);

private class NewsAdapter extends CommonRecyclerAdapter<News>{

    public NewsAdapter(Context context, int layoutResId, List<News> data) {
        super(context, layoutResId, data);
    }

    @Override public void onUpdate(BaseAdapterHelper helper, News item, int position) {
        helper.setText(R.id.xxx, item.getTitle())
              .setImageLoad(new GlideImageLoad())
              .setImageUrl(R.id.xxx,item.getCoverUrl());
    }
}

//多布局文件
private final class MultipleLayoutAdapter extends CommonRecyclerAdapter<News>{

    public MultipleLayoutAdapter(Context context, int layoutResId, List<News> data) {
        super(context, layoutResId, data);
    }
    //多种布局重写此方法即可
    @Override public int getLayoutResId(News item, int position) {
        int layoutResId = -1;
        switch (item.getNewsType()){
            case News.TYPE_NONE_PICTURE: //布局样式一
                layoutResId = R.layout.item_none_picture;
                break;
            case News.TYPE_SINGLE_PICTURE: //布局样式二
                layoutResId = R.layout.item_single_picture;
                break;
            case News.TYPE_MULTIPLE_PICTURE: //布局样式三
                layoutResId = R.layout.item_multiple_picture;
                break;

            更多的布局样式 ...
        }
        return layoutResId;
    }

    @Override public void onUpdate(BaseAdapterHelper helper, News item, int position) {
        helper.setImageLoad(new GlideImageLoad());
        switch (item.getNewsType()){
            case News.TYPE_NONE_PICTURE: //布局样式一
                helper.setText(R.id.xxx, item.getTitle())
                      .setImageUrl(R.id.xxx,item.getCoverUrl());
                break;
            case News.TYPE_SINGLE_PICTURE: //布局样式二
                helper.setText(R.id.xxx, item.getTitle())
                      .setImageUrl(R.id.xxx,item.getCoverUrl());
                break;
            case News.TYPE_MULTIPLE_PICTURE: //布局样式三
                helper.setText(R.id.xxx, item.getTitle())
                      .setImageUrl(R.id.xxx,item.getCoverUrl());
                break;

            更多的布局样式 ...
        }
    }
}
```

RecyclerView item点击事件和长按事件
```java
commonRecyclerAdapter.setOnItemClickListener(new CommonRecyclerAdapter.OnItemClickListener() {
    @Override
    public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
        // TODO ...
    }
});
commonRecyclerAdapter.setOnItemLongClickListener(new CommonRecyclerAdapter.OnItemLongClickListener() {
    @Override public void onItemLongClick(RecyclerView.ViewHolder viewHolder, View view,
        int position) {
        // TODO ...
    }
});
```

CommonAdapter、CommonRecyclerAdapter区别
```java
CommonAdapter适用于：ListView/GridView
CommonRecyclerAdapter适用于：RecyclerView
使用方式都一样
```

BaseAdapterHelper 使用示例
```java
helper.setText(R.id.viewId, text)
      .setTag(R.id.viewId, objectTag)
      .setAlpha(R.id.viewId, 0.6f)
      .setBackgroundColor(R.id.viewId, R.color.colorResId)
      .setBackgroundRes(R.id.viewId, R.drawable.drawableResId)
      .setChecked(R.id.viewId, true)
      .setImageBitmap(R.id.viewId,bitmap)
      .setImageDrawable(R.id.viewId,drawable)
      .setImageResource(R.id.viewId, R.drawable.drawableResId)
      .setImageUrl(R.id.viewId, url)
      .setProgress(R.id.viewId,progress)
      .setProgress(R.id.viewId,progress,max)
      .setRating(R.id.viewId, rating)
      .setRating(R.id.viewId, rating, max)
      .setTextColor(R.id.viewId, R.color.colorResId)
      .setTextColorRes(R.id.viewId, R.color.colorResId)
      .setTextColorRes(R.id.viewId, R.color.colorResId, theme) //New in version 1.2
      //TextView添加超链接，更多属性参考：android.text.util.Linkify#addLinks(TextView text, int mask)
      .addLinks(R.id.viewId, , Linkify.ALL) //New in version 1.2
      //单个TextView设置Typeface
      .setTypeface(R.id.viewId, typeface)
      //多个TextView设置Typeface
      .setTypeface(typeface, R.id.xxx1, R.id.xxx2,R.id.xxx3, ...)
      .setVisible(R.id.viewId, View.VISIBLE)
      //ProgressBar设置Max
      .setMax(R.id.viewId, max)
      //ListView设置adapter
      .setAdapter(R.id.viewId, adapter)
      //下面为View常用点击事件设置
      .setOnTouchListener(R.id.viewId, View.OnTouchListener)
      .setOnClickListener(R.id.viewId, View.OnClickListener)
      .setOnLongClickListener(R.id.viewId, View.OnLongClickListener)
      .setOnItemClickListener(R.id.viewId, AdapterView.OnItemClickListener)
      .setOnItemLongClickListener(R.id.viewId, AdapterView.OnItemLongClickListener)
      .setOnItemSelectedClickListener(R.id.viewId, AdapterView.OnItemSelectedListener);

//获取item的convertView
View convertView = helper.getView();

//如果上面的属性不够用,可以通过getView(viewId)拿到View,然后进行属性设置
View childView = helper.getView(R.id.viewId);

```

自定义图片加载
```java
public class YourXXX implements ImageLoad {

    @Override public void load(Context context, ImageView imageView, String imageUrl) {

        //使用Glide加载图片
        Glide.with(context).load(imageUrl).into(imageView);

        or

        //使用Picasso加载图片
        Picasso.with(context).load(url).into(imageView);

        or

        fresco
        Android-Universal-Image-Loader
        自定义
        ...
    }
}
```

注意事项
```java
//加载网络图片之前，请调用setImageLoad方法，设置网络图片加载的实现类
helper.setImageLoad(new GlideImageLoad());
helper.setImageUrl(R.id.xxx,url);
```

常用的数据操作
```java
//CommonAdapter、CommonRecyclerAdapter都实现了IData接口，里面包含了一些常用的数据操作

void add(T elem);

void addAll(List<T> elem);

void set(T oldElem, T newElem);

void set(int index, T elem);

void remove(T elem);

void remove(int index);

void replaceAll(List<T> elem);

boolean contains(T elem);

void clear();

```

