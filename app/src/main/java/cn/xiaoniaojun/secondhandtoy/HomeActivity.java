package cn.xiaoniaojun.secondhandtoy;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import cn.xiaoniaojun.bottomnavigationbar.BottomBarTab;
import cn.xiaoniaojun.bottomnavigationbar.BottomNavigationBar;
import cn.xiaoniaojun.secondhandtoy.ui.fragments.HomePage.HomeFragment;
import cn.xiaoniaojun.secondhandtoy.ui.fragments.UserInfoPage.UserInfoFragment;
import cn.xiaoniaojun.secondhandtoy.ui.interfaces.OnBackToFirstFragmentListener;
import cn.xiaoniaojun.secondhandtoy.ui.widgets.container_view.ContainerView;
import cn.xiaoniaojun.secondhandtoy.ui.widgets.group_view.GroupViewDescriptor;
import cn.xiaoniaojun.secondhandtoy.ui.widgets.group_view.GroupViewFactory;
import cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.OnRowClickListener;
import cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.normal_row_view.NormalRowViewDescriptor;
import cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.normal_row_view.NormalRowViewFactory;
import cn.xiaoniaojun.secondhandtoy.ui.widgets.row_view.user_info_row_view.UserInfoRowViewFactory;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;


/**
 * Package: com.github.ypicoleal.logindemo
 * Created by hackpoint on 2017/5/6.
 */

public class HomeActivity extends SupportActivity implements OnRowClickListener, OnBackToFirstFragmentListener{

    public static final int ROW_ACTION_FIRST = 1;
    public static final int ROW_ACTION_SECOND = 2;

    // Root Fragments
    // 分别表示(1)首页、(2)正在出售的玩具列表页、(3)玩具出售发布页面、(4)用户个人信息页面
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_OFFER_TOYS = 1;
    private static final int FRAGMENT_SALE_TOYS = 2;
    private static final int FRAGMENT_USER_INFO = 3;



    private SupportFragment[] mFragments = new SupportFragment[4];

    private BottomNavigationBar mBottomNavigationBar;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        findViewsId();

        setSupportActionBar(mToolbar);


        // 由于登陆页面效果中更改了系统状态栏背景色，
        // 这里需要将系统状态栏的背景色还原为主题颜色。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, android.R.color.holo_orange_light));
        }




        // 创建并设置4个主(Root)Fragment
        setupRootFragments(savedInstanceState);
        // 设置底部导航条(Bottom Navigation Bar)
        initBottomNavigationBar();
    }

    private void setupRootFragments(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mFragments[FRAGMENT_HOME] = HomeFragment.newInstance();
            mFragments[FRAGMENT_USER_INFO] = UserInfoFragment.newInstance();
            loadMultipleRootFragment(R.id.fl_fragment_holder,FRAGMENT_HOME,mFragments[FRAGMENT_HOME],mFragments[FRAGMENT_USER_INFO]);
        } else {

            mFragments[FRAGMENT_HOME] = findFragment(HomeFragment.class);
            mFragments[FRAGMENT_USER_INFO] = findFragment(UserInfoFragment.class);
        }

    }


    private void findViewsId() {
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

    }


    private void initBottomNavigationBar() {
        mBottomNavigationBar.addTab(R.drawable.selector_home, "主页", 0xffdfb052);
        mBottomNavigationBar.addTab(R.drawable.selector_home, "玩具", 0xff12b052);
        mBottomNavigationBar.addTab(R.drawable.selector_home, "发布", 0xff342052);
        mBottomNavigationBar.addTab(R.drawable.selector_home, "我", 0xffdf2052);
        mBottomNavigationBar.setTextDefaultVisible(true);
        mBottomNavigationBar.setOnTabListener(new BottomNavigationBar.TabListener() {
            @Override
            public void onSelected(BottomBarTab tab, int position, int preposition) {
                Log.v("MainActivity","position:"+position+"preposition:"+preposition);
                showHideFragment(mFragments[position], mFragments[preposition]);
                switch (position) {
                    case FRAGMENT_HOME:
                        Toast.makeText(getApplicationContext(), "Home Fragment Loaded!", Toast.LENGTH_SHORT).show();
                        break;
                    case FRAGMENT_OFFER_TOYS:
                        Toast.makeText(getApplicationContext(), "ToysOnOffer Fragment Loaded", Toast.LENGTH_SHORT).show();
                        break;
                    case FRAGMENT_SALE_TOYS:
                        break;
                    case FRAGMENT_USER_INFO:


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
        }, 300);
    }


    @Override
    public void onRowClick(int action) {
        Toast.makeText(this, "On Row click: " + action, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackToFirstFragment() {

    }

    //TODO: OnBackPressToFragment
}
