package com.github.ypicoleal.logindemo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.ypicoleal.logindemo.widgets.RowActionEnum;
import com.github.ypicoleal.logindemo.widgets.view_factories.row_view.OnRowClickListener;
import com.github.ypicoleal.logindemo.widgets.view_factories.row_view.RowView;
import com.github.ypicoleal.logindemo.widgets.view_factories.row_view.RowViewFactory;
import com.github.ypicoleal.logindemo.widgets.view_factories.row_view.RowViewParams;

import android.os.Handler;

import cn.xiaoniaojun.bottomnavigationbar.BottomBarTab;
import cn.xiaoniaojun.bottomnavigationbar.BottomNavigationBar;


/**
 * Package: com.github.ypicoleal.logindemo
 * Created by hackpoint on 2017/5/6.
 */

public class HomeActivity extends AppCompatActivity implements OnRowClickListener {

    public static final int ROW_ACTION_FIRST = 1;

    private RowView mRowFirst;
    private RowView mRowSecond;

    private BottomNavigationBar mBottomNavigationBar;
    private LinearLayout mHomeLayoutContainer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        }

        findViews();
        initBottomNavigationBar();
    }



    private void findViews() {
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mHomeLayoutContainer = (LinearLayout) findViewById(R.id.home_layout_container);
        RowViewFactory rowViewFactory = new RowViewFactory(this);
        RowView firstRowView = rowViewFactory.createView(
                RowViewParams.build()
                        .setIconAndContent(R.drawable.ic_qq_blue, "MY POSTS").
                        setOnRowAction(ROW_ACTION_FIRST)
                        .done()
        );
        mHomeLayoutContainer.addView(firstRowView,0);
        
    }

 

    private void initBottomNavigationBar() {
        mBottomNavigationBar.addTab(R.drawable.selector_home,"微博",0xffdfb052);
        mBottomNavigationBar.addTab(R.drawable.selector_home,"微博",0xffdfb052);
        mBottomNavigationBar.addTab(R.drawable.selector_home,"QQ",0xffdf2052);
        mBottomNavigationBar.addTab(R.drawable.selector_home,"QQ",0xffdf2052);
        mBottomNavigationBar.setOnTabListener(new BottomNavigationBar.TabListener() {
            @Override
            public void onSelected(BottomBarTab tab, int position) {
                switch (position) {
                    case 0:
                        Toast.makeText(getApplicationContext(),"On BottomBar Item Selected: "+ position,Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(),"On BottomBar Item Selected: " + position,Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        // 在系统执行动画(或者正在施法的时候)更改视图树可能会导致无法立即重新layout，
        // 此时需要delay调用
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mBottomNavigationBar.requestLayout();
            }
        },100);
    }


    
    @Override
    public void onRowClick(int action) {
        Toast.makeText(this,"On Row click: " + action,Toast.LENGTH_SHORT).show();
    }
}
