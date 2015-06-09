package com.yizi.iwuse.product;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yizi.iwuse.R;
import com.yizi.iwuse.common.base.BaseActivity;
import com.yizi.iwuse.common.widget.WebViewWidget;

public class ProductDetailActivity extends BaseActivity implements
	OnClickListener {

    @ViewInject(R.id.wv_product_detail)
    private WebViewWidget wv_product_detail;
    @ViewInject(R.id.btn_add_cart)
    private Button btn_add_cart;
    @ViewInject(R.id.cart_anim_icon)
    private ImageView cart_anim_icon;
    @ViewInject(R.id.btn_back)
    private Button btn_back;
    private Animation mAnimation;

    @Override
    public void removeAllView() {
	// TODO Auto-generated method stub

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);

	setContentView(R.layout.layout_productdetail);
	ViewUtils.inject(this);

	wv_product_detail.loadUrl("file:///android_asset/index.html");
	mAnimation = AnimationUtils.loadAnimation(this, R.anim.cart_anim);
	mAnimation.setAnimationListener(new AnimationListener() {

	    @Override
	    public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		cart_anim_icon.setVisibility(View.GONE);
	    }

	    @Override
	    public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	    }

	});
	btn_add_cart.setOnClickListener(this);
	btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
	// TODO Auto-generated method stub
	if (v == btn_add_cart) {
	    cart_anim_icon.setVisibility(View.VISIBLE);
	    cart_anim_icon.startAnimation(mAnimation);
	} else if (v == btn_back) {
	    finish();
	}
    }

}
