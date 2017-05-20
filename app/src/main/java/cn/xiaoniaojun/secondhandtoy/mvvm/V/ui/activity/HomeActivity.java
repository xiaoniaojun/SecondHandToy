package cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import cn.xiaoniaojun.secondhandtoy.mvvm.V.layer.MainHomeLayer;
import cn.xiaoniaojun.secondhandtoy.mvvm.VM.IMainHomeViewModel;
import cn.xiaoniaojun.secondhandtoy.mvvm.VM.impl.MainHomeViewModel;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.interfaces.OnBackToFirstFragmentListener;
import cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.widget.row_view.OnRowClickListener;
import me.yokeyword.fragmentation.SupportActivity;


/**
 * Package: com.github.ypicoleal.logindemo
 * Created by hackpoint on 2017/5/6.
 */

public class HomeActivity extends SupportActivity implements OnRowClickListener, OnBackToFirstFragmentListener{

    public static final int ROW_ACTION_FIRST = 1;
    public static final int ROW_ACTION_SECOND = 2;

    private IMainHomeViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new MainHomeViewModel(new MainHomeLayer(this, savedInstanceState));
        mViewModel.bind();





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
