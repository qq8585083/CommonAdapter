package com.hy.commonadapter.simple.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.hy.commonadapter.BaseAdapterHelper;
import com.hy.commonadapter.CommonAdapter;
import com.hy.commonadapter.simple.R;
import com.hy.commonadapter.simple.bean.News;
import com.hy.commonadapter.simple.consts.Consts;
import com.hy.commonadapter.simple.data.NewsDataSource;

import java.util.Date;
import java.util.Locale;

public class ListViewSimpleActivity extends AppCompatActivity {
    private ListView mListView;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        //这里偷懒，使用默认的。实际项目中建议使用ToolBar
        getSupportActionBar().setTitle(R.string.main_listview_simple_lable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView = (ListView) findViewById(R.id.listview);
        mListView.setAdapter(new CommonAdapter<News>(this, R.layout.item_none_picture,
                NewsDataSource.getNewsList()) {
            @Override public void onUpdate(BaseAdapterHelper helper, News item, int position) {
                helper.setText(R.id.item_none_picture_title, item.getTitle())
                      .setText(R.id.item_none_picture_author,
                              String.format(Locale.CHINA, Consts.FORMAT_AUTHOR, item.getAuthor()))
                      .setText(R.id.item_none_picture_date,
                              Consts.DATE_FORMAT.format(new Date(item.getReleaseTime())))
                      .setText(R.id.item_none_picture_intro, item.getIntro());
            }
        });

    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
