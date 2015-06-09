package com.yizi.iwuse.filter.view;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yizi.iwuse.R;
import com.yizi.iwuse.common.widget.NoScrollGridView;
import com.yizi.iwuse.filter.model.ProductFilterItem;
import com.yizi.iwuse.filter.service.FilterService;
import com.yizi.iwuse.filter.service.events.ProductFilterEvent;
import com.yizi.iwuse.general.MainHomeActivity;
import com.yizi.iwuse.product.service.ProductService;
import com.yizi.iwuse.product.service.events.ThemeEvent;
import com.yizi.iwuse.product.view.WuseThemeFragment.ViewHolder;

import de.greenrobot.event.EventBus;

/**
 * 筛选商品信息
 * 
 * @author hehaodong
 *
 */
public class ProductFilterFragment extends Fragment {

    private ListView lv_filter;
    private ArrayList<ProductFilterItem> filterArray;
    private FilterAdapter filterAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
	super.onActivityCreated(savedInstanceState);
	EventBus.getDefault().register(this);
	FilterService server = new FilterService();
	server.doNetWork();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
	View view = inflater.inflate(R.layout.frg_filter, null);
	return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	lv_filter = (ListView) view.findViewById(R.id.lv_filter);
	filterArray = new ArrayList<ProductFilterItem>();
	filterAdapter = new FilterAdapter();
	lv_filter.setAdapter(filterAdapter);
    }

    private class FilterAdapter extends BaseAdapter {

	@Override
	public int getCount() {
	    // TODO Auto-generated method stub
	    return filterArray.size();
	}

	@Override
	public Object getItem(int position) {
	    // TODO Auto-generated method stub
	    return filterArray.get(position);
	}

	@Override
	public long getItemId(int position) {
	    // TODO Auto-generated method stub
	    return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    // TODO Auto-generated method stub
	    ViewHolder viewHolder;
	    ProductFilterItem productFilterItem = filterArray.get(position);
	    if (convertView == null) {
		viewHolder = new ViewHolder();
		convertView = LayoutInflater.from(getActivity()).inflate(
			R.layout.product_filter_item, null);
		viewHolder.tv_name = (TextView) convertView
			.findViewById(R.id.tv_name);
		viewHolder.gv_filter = (NoScrollGridView) convertView
			.findViewById(R.id.gv_filter);
		convertView.setTag(viewHolder);
	    } else {
		viewHolder = (ViewHolder) convertView.getTag();
	    }

	    String text = productFilterItem.getFilterName();
	    viewHolder.tv_name.setText(text);
	    FilterGvAdapter filterGvAdapter = new FilterGvAdapter(
		    productFilterItem.getFilterArray());
	    viewHolder.gv_filter.setAdapter(filterGvAdapter);
	    return convertView;
	}

	private class ViewHolder {
	    public TextView tv_name;
	    public NoScrollGridView gv_filter;
	}

    }

    public class FilterGvAdapter extends BaseAdapter {

	private ArrayList<String> filterInsertArray;

	public FilterGvAdapter(ArrayList<String> filterArray) {
	    // TODO Auto-generated constructor stub
	    this.filterInsertArray = filterArray;
	}

	@Override
	public int getCount() {
	    // TODO Auto-generated method stub
	    return filterInsertArray.size();
	}

	@Override
	public Object getItem(int position) {
	    // TODO Auto-generated method stub
	    return filterInsertArray.get(position);
	}

	@Override
	public long getItemId(int position) {
	    // TODO Auto-generated method stub
	    return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    // TODO Auto-generated method stub
	    ViewHolder viewHolder;
	    if (convertView == null) {
		viewHolder = new ViewHolder();
		convertView = LayoutInflater.from(getActivity()).inflate(
			R.layout.single_text, null);
		viewHolder.tv_select = (TextView) convertView
			.findViewById(R.id.tv_select);
		convertView.setTag(viewHolder);
	    } else {
		viewHolder = (ViewHolder) convertView.getTag();
	    }

	    viewHolder.tv_select.setOnClickListener(new TextViewClick());
	    viewHolder.tv_select.setText(filterInsertArray.get(position));
	    return convertView;
	}

	private class ViewHolder {
	    public TextView tv_select;
	}

	private class TextViewClick implements OnClickListener {

	    @Override
	    public void onClick(View view) {
		// TODO Auto-generated method stub
		TextView textView = (TextView) view;
		if (textView.isSelected()) {
		    textView.setSelected(false);
		} else {
		    textView.setSelected(true);
		}
	    }

	}

    }

    public void onEventMainThread(ProductFilterEvent event) {
	filterArray = event.getFilterArray();
	filterAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
	// TODO Auto-generated method stub
	EventBus.getDefault().unregister(this);
	super.onDestroy();
    }

}
