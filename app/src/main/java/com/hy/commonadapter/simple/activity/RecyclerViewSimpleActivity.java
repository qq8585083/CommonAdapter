package com.hy.commonadapter.simple.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hy.commonadapter.BaseAdapterHelper;
import com.hy.commonadapter.CommonRecyclerAdapter;
import com.hy.commonadapter.simple.R;
import com.hy.commonadapter.simple.bean.News;
import com.hy.commonadapter.simple.consts.Consts;
import com.hy.commonadapter.simple.data.NewsDataSource;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecyclerViewSimpleActivity extends AppCompatActivity implements
    CommonRecyclerAdapter.OnItemClickListener, CommonRecyclerAdapter.OnItemLongClickListener {
    private RecyclerView mRecyclerView;
    private NewsAdapter  mNewsAdapter;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        //这里偷懒，使用默认的。实际项目中建议使用ToolBar
        getSupportActionBar().setTitle(R.string.main_recyclerview_simple_lable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mNewsAdapter = new NewsAdapter(this, R.layout.item_none_picture,
                NewsDataSource.getNewsList());
        mNewsAdapter.setOnItemClickListener(this);
        mNewsAdapter.setOnItemLongClickListener(this);
        mRecyclerView.setAdapter(mNewsAdapter);
    }


    @Override public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
        Toast.makeText(RecyclerViewSimpleActivity.this, "RecyclerView onItemClick,position:" + position,
            Toast.LENGTH_SHORT).show();
    }

    @Override public void onItemLongClick(RecyclerView.ViewHolder viewHolder,View view, int position) {
        Toast.makeText(RecyclerViewSimpleActivity.this, "RecyclerView onItemLongClick,position:"+position, Toast.LENGTH_SHORT).show();
    }

    private class NewsAdapter extends CommonRecyclerAdapter<News>{

        public NewsAdapter(Context context, int layoutResId, List<News> data) {
            super(context, layoutResId, data);
        }

        @Override public void onUpdate(BaseAdapterHelper helper, News item, int position) {
            helper.setText(R.id.item_none_picture_title, item.getTitle())
                .setText(R.id.item_none_picture_author,
                    String.format(Locale.CHINA, Consts.FORMAT_AUTHOR, item.getAuthor()))
                .setText(R.id.item_none_picture_date,
                    Consts.DATE_FORMAT.format(new Date(item.getReleaseTime())))
                .setText(R.id.item_none_picture_intro, item.getIntro());
        }
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
