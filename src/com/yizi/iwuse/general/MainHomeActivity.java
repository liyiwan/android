package com.yizi.iwuse.general;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.yizi.iwuse.R;
import com.yizi.iwuse.common.base.IActivity;
import com.yizi.iwuse.common.utils.ILog;
import com.yizi.iwuse.constants.UserConst;
import com.yizi.iwuse.filter.view.ProductFilterFragment;
import com.yizi.iwuse.general.view.MainHomeFragment;
import com.yizi.iwuse.user.service.events.UserEvents;
import com.yizi.iwuse.user.view.UserFragment;

import de.greenrobot.event.EventBus;

/**
 * 主页activity
 * 
 * @author hehaodong
 *
 */
public class MainHomeActivity extends FragmentActivity implements IActivity {

    private static final String TAG = "MainHomeActivity";
    private ActionBarDrawerToggle drawerbar;
    private MainHomeFragment mainHomeFragment;
    private FragmentManager mFragmentManager;
    /*** 用户中心 ***/
    private UserFragment userFragment;
    /*** 筛选页面 ***/
    private ProductFilterFragment productFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	this.setContentView(R.layout.layout_mainhome);
	initView();
	EventBus.getDefault().register(this);
    }

    /***
     * 初始化控件
     */
    private void initView() {
	mainHomeFragment = new MainHomeFragment();
	mFragmentManager = getSupportFragmentManager();
	FragmentTransaction f_transaction = mFragmentManager.beginTransaction();
	f_transaction.replace(R.id.center_layout, mainHomeFragment);
	f_transaction.commitAllowingStateLoss();
    }

    /***
     * 打开用户中心菜单
     */
    public void openUserCenterLayout() {

	FragmentTransaction transaction = mFragmentManager.beginTransaction();
	transaction.setCustomAnimations(R.anim.push_left_in,
		R.anim.push_left_out);
	// hideFragments(transaction);
	if (null == userFragment) {
	    userFragment = new UserFragment();
	    transaction.add(R.id.center_layout, userFragment);
	} else {
	    transaction.show(userFragment);
	}
	transaction.commitAllowingStateLoss();
    }

    /**
     * 关闭用户中心菜单
     */
    public void closeUserCenter() {
	FragmentTransaction transaction = mFragmentManager.beginTransaction();
	transaction.setCustomAnimations(R.anim.push_right_in,
		R.anim.push_right_out);
	if (null != userFragment) {
	    transaction.hide(userFragment);
	    /*
	     * if (null == mainHomeFragment) { mainHomeFragment = new
	     * MainHomeFragment(); transaction.add(R.id.center_layout,
	     * mainHomeFragment); } else { transaction.show(mainHomeFragment); }
	     */
	    transaction.commitAllowingStateLoss();
	}
    }

    /**
     * 关闭所有fragment
     * 
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
	/*
	 * if (null != mainHomeFragment) { transaction.hide(mainHomeFragment); }
	 */
	if (null != userFragment) {
	    transaction.hide(userFragment);
	}
    }

    /***
     * 打开产品筛选
     */
    /*
     * public void openProductSearchLayout() {
     * 
     * FragmentTransaction transaction = mFragmentManager.beginTransaction(); //
     * transaction.setCustomAnimations(R.anim.push_right_in,
     * R.anim.push_right_out); // hideFragments(transaction); if (null ==
     * productFragment) { productFragment = new ProductSearchFragment();
     * transaction.add(R.id.ll_filter_fragment, productFragment); } else {
     * transaction.show(productFragment); }
     * transaction.commitAllowingStateLoss();
     * 
     * }
     */

    /**
     * 关闭产品筛选
     */
    public void closeProductSearch() {
	FragmentTransaction transaction = mFragmentManager.beginTransaction();
	transaction.setCustomAnimations(R.anim.push_left_in,
		R.anim.push_left_out);
	if (null != productFragment) {
	    transaction.hide(productFragment);
	    if (null == mainHomeFragment) {
		mainHomeFragment = new MainHomeFragment();
		transaction.add(R.id.center_layout, mainHomeFragment);
	    } else {
		transaction.show(mainHomeFragment);
	    }
	    transaction.commitAllowingStateLoss();
	}
    }

    @Override
    protected void onDestroy() {
	super.onDestroy();
	EventBus.getDefault().unregister(this);
    }

    @Override
    public void removeAllView() {

    }

    /****
     * 用户监后台事件
     * 
     * @param userEvent
     */
    public void onEventMainThread(UserEvents userEvent) {
	ILog.i(TAG, "onEventMainThread");
	userFragment.onEvent(userEvent);
    }

}
