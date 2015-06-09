package com.yizi.iwuse.product.view;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import com.yizi.iwuse.AppContext;
import com.yizi.iwuse.R;
import com.yizi.iwuse.common.utils.IWuseUtil;
import com.yizi.iwuse.common.widget.ThemeVideoWidget;
import com.yizi.iwuse.common.widget.VideoThread;
import com.yizi.iwuse.common.widget.VideoWidget;
import com.yizi.iwuse.general.service.GeneralService;
import com.yizi.iwuse.general.view.MainHomeFragment;
import com.yizi.iwuse.product.ProductDetailActivity;
import com.yizi.iwuse.product.model.ThemeItem;
import com.yizi.iwuse.product.service.ProductService;
import com.yizi.iwuse.product.service.events.ThemeEvent;
import com.yizi.iwuse.product.service.events.VideoEvent;

import de.greenrobot.event.EventBus;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 物色页面
 * 
 * @author hehaodong
 *
 */
public class WuseThemeFragment extends Fragment implements OnItemClickListener {

    private FirstItemMaxListView mListView;
    private FirstItemMaxAdapter mAdapter;
    /*** 列表项最大高度 ***/
    private int maxHeight = 0;
    /*** 列表项标准高度 ***/
    private int firstHeight = 0;
    /*** 是否是第一次加载listview ***/
    private boolean isFisrt = true;
    private ViewGroup container;
    /*** 主题数据 ***/
    private ArrayList<ThemeItem> themeArray;
    /*** 是否有在播放视频 ***/
    private boolean isVideoShow = false;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
	this.container = container;
	View mView = inflater
		.inflate(R.layout.frg_wuse_theme, container, false);
	mListView = (FirstItemMaxListView) mView
		.findViewById(R.id.firstItemMaxListView);
	return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onActivityCreated(savedInstanceState);

	EventBus.getDefault().register(this);
	WindowManager wm = (WindowManager) getActivity().getSystemService(
		Context.WINDOW_SERVICE);
	/*** 屏幕高度 ***/
	int mScreenHeight = wm.getDefaultDisplay().getHeight();
	/*** 状态栏高度 ***/
	int statusHeight = IWuseUtil.getStatusBarHeight(getActivity());

	maxHeight = (mScreenHeight - MainHomeFragment.titleHeight - statusHeight) / 5 * 3;// 列表项最大高度
	firstHeight = (mScreenHeight - MainHomeFragment.titleHeight - statusHeight) / 5;// 列表项起始高度

	mAdapter = new FirstItemMaxAdapter();
	mListView.setAdapter(mAdapter);
	mListView.setItemHeight(firstHeight);
	mListView.setItemMaxHeight(maxHeight);
	mListView.setOnItemClickListener(this);
	ProductService server = AppContext.instance().productService;
	server.doNetWork();
    }

    private class FirstItemMaxAdapter extends BaseAdapter {

	@Override
	public int getCount() {
	    return (themeArray == null) ? 0 : themeArray.size();
	}

	@Override
	public Object getItem(int i) {
	    return themeArray.get(i);
	}

	@Override
	public long getItemId(int i) {
	    return i;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {

	    ViewHolder viewHolder;
	    ThemeItem themeItem = themeArray.get(position);

	    if (view == null) {
		view = LayoutInflater.from(getActivity()).inflate(
			R.layout.first_item_max_item, null);
		viewHolder = new ViewHolder();
		viewHolder.surface = (SurfaceView) view
			.findViewById(R.id.vdovi_videotools);
		viewHolder.cover = (ImageView) view.findViewById(R.id.cover);
		viewHolder.tv_title = (TextView) view
			.findViewById(R.id.tv_title);
		viewHolder.tv_kind = (TextView) view.findViewById(R.id.tv_kind);
		viewHolder.tv_property = (TextView) view
			.findViewById(R.id.tv_property);
		viewHolder.fl_insert_large = (FrameLayout) view
			.findViewById(R.id.fl_insert_large);
		viewHolder.tv_grey = (TextView) view.findViewById(R.id.tv_grey);
		view.setTag(viewHolder);
	    } else {
		viewHolder = (ViewHolder) view.getTag();
	    }

	    viewHolder.object = themeItem;
	    viewHolder.tv_title.setText(themeItem.title);
	    viewHolder.tv_kind.setText(themeItem.kind);
	    viewHolder.tv_property.setText(themeItem.property);
	    viewHolder.fl_insert_large
		    .setLayoutParams(new FrameLayout.LayoutParams(
			    FrameLayout.LayoutParams.MATCH_PARENT, maxHeight));
	    if ("视频".equals(themeItem.property) && isVideoShow) {
		viewHolder.surface.setVisibility(View.VISIBLE);
		viewHolder.cover.setVisibility(View.GONE);
		if (viewHolder.videoView == null) {
		    // String vdoPath =
		    // "android.resource://"+getActivity().getPackageName()+"/"+R.raw.demo3;
		    viewHolder.videoView = new ThemeVideoWidget(getActivity(),
			    viewHolder.surface, themeItem.videoUrl, maxHeight);
		    isVideoShow = false;
		}

	    } else {
		viewHolder.surface.setVisibility(View.GONE);
		viewHolder.cover.setVisibility(View.VISIBLE);
		viewHolder.cover.setScaleType(ImageView.ScaleType.CENTER_CROP);
		viewHolder.cover.setImageResource(themeItem.picUrl);
	    }

	    if (isFisrt) {
		if (position == 0) {
		    view.setLayoutParams(new AbsListView.LayoutParams(
			    AbsListView.LayoutParams.MATCH_PARENT, maxHeight));
		    viewHolder.tv_grey.getBackground().setAlpha(0);
		    new VideoThread(viewHolder).start();
		} else {
		    view.setLayoutParams(new AbsListView.LayoutParams(
			    AbsListView.LayoutParams.MATCH_PARENT, firstHeight));
		    // viewHolder.tv_grey.getBackground().setAlpha(127);
		}
		if (position == 3) {
		    isFisrt = false;
		}
	    }
	    return view;
	}

    }

    public class ViewHolder {
	public ImageView cover;
	public SurfaceView surface;
	private TextView tv_title;
	private TextView tv_kind;
	private TextView tv_favor;
	private TextView tv_property;
	public View videoView;
	public LinearLayout ll_video;
	private FrameLayout fl_insert_large;
	public TextView tv_grey;
	public Object object;
    }

    public void onEventMainThread(ThemeEvent event) {
	themeArray = event.getThemeArray();
	mAdapter.notifyDataSetChanged();
    }

    public void onEventMainThread(ViewHolder viewHolder) {
	View view = mListView.getChildAt(0);
	if (view != null) {
	    ViewHolder viewHolder2 = (ViewHolder) view.getTag();
	    if (viewHolder2 != null) {
		ThemeItem themeItem = (ThemeItem) viewHolder2.object;
		if ("视频".equals(themeItem.property)) {
		    if (!FirstItemMaxListView.isFingerPress) {
			if (viewHolder2.videoView == null) {
			    isVideoShow = true;
			    mAdapter.notifyDataSetChanged();
			}
		    }
		}
	    }
	}
    }

    @Override
    public void onResume() {
	// TODO Auto-generated method stub
	super.onResume();

	View view = mListView.getChildAt(0);
	if (view != null) {
	    ViewHolder viewHolder = (ViewHolder) view.getTag();
	    ThemeItem themeItem = (ThemeItem) viewHolder.object;
	    if ("视频".equals(themeItem.property)) {
		if (viewHolder.videoView == null) {
		    new VideoThread(viewHolder).start();
		}
	    }
	}
    }

    @Override
    public void onStop() {
	// TODO Auto-generated method stub
	super.onStop();

	View view = mListView.getChildAt(0);
	if (view != null) {
	    ViewHolder viewHolder = (ViewHolder) view.getTag();
	    ThemeItem themeItem = (ThemeItem) viewHolder.object;
	    if ("视频".equals(themeItem.property)) {
		ThemeVideoWidget videoWidget = (ThemeVideoWidget) viewHolder.videoView;
		if (videoWidget != null) {
		    if (videoWidget.getPlayer() != null) {
			videoWidget.getPlayer().release();
			viewHolder.surface.setVisibility(View.GONE);
			viewHolder.cover.setVisibility(View.VISIBLE);
			videoWidget.setPlayer(null);
			viewHolder.videoView = null;
		    }
		}
	    }
	}
    }

    @Override
    public void onDestroy() {
	// TODO Auto-generated method stub
	EventBus.getDefault().unregister(this);
	super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
	    long id) {
	// TODO Auto-generated method stub
	Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
	startActivity(intent);
    }

}
