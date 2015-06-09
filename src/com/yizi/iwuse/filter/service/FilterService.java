package com.yizi.iwuse.filter.service;

import java.util.ArrayList;

import com.yizi.iwuse.R;
import com.yizi.iwuse.common.base.ICoreService;
import com.yizi.iwuse.filter.model.ProductFilterItem;
import com.yizi.iwuse.filter.service.events.ProductFilterEvent;
import com.yizi.iwuse.filter.service.events.ThemeFilterEvent;
import com.yizi.iwuse.product.service.events.ThemeEvent;

import de.greenrobot.event.EventBus;

public class FilterService implements ICoreService {

    /*** 主题过滤数据 ***/
    private ArrayList<String> themeArray;
    /*** 单品过滤数据 ***/
    private ArrayList<ProductFilterItem> filterArray;

    @Override
    public boolean initState() {
	// TODO Auto-generated method stub
	return true;
    }

    public void getNetData() {
	filterArray = new ArrayList<ProductFilterItem>();
	ArrayList<String> filterArray1 = new ArrayList<String>();
	filterArray1.add("厨房");
	filterArray1.add("卫浴间");
	filterArray1.add("卧房");
	filterArray1.add("大厅");
	filterArray1.add("户外");
	ProductFilterItem filter1 = new ProductFilterItem("房间", filterArray1);
	filterArray.add(filter1);
	ArrayList<String> filterArray2 = new ArrayList<String>();
	filterArray2.add("美欧城市");
	filterArray2.add("北欧简约");
	filterArray2.add("日式淡雅");
	filterArray2.add("东南亚度假");
	filterArray2.add("华丽宫廷");
	filterArray2.add("奢华现代");
	ProductFilterItem filter2 = new ProductFilterItem("风格", filterArray2);
	filterArray.add(filter2);
	ArrayList<String> filterArray3 = new ArrayList<String>();
	filterArray3.add("沙发");
	filterArray3.add("桌子");
	filterArray3.add("椅子");
	filterArray3.add("床");
	filterArray3.add("柜子");
	filterArray3.add("衣柜");
	filterArray3.add("茶几");
	filterArray3.add("灯饰");
	filterArray3.add("饰品");
	ProductFilterItem filter3 = new ProductFilterItem("类别", filterArray3);
	filterArray.add(filter3);

    }

    public void doNetWork() {
	getNetData();
	ProductFilterEvent productFilterEvent = new ProductFilterEvent();
	productFilterEvent.setFilterArray(filterArray);
	EventBus.getDefault().post(productFilterEvent);
    }

    public void getThemeFilterData() {
	themeArray = new ArrayList<String>();
	themeArray.add("All is iwuse");
	themeArray.add("Life is iwuse");
	themeArray.add("Video as iwuse");
	themeArray.add("User as iwuse");
    }

    public void doThemeFilterWork() {
	getThemeFilterData();
	ThemeFilterEvent themeFilterEvent = new ThemeFilterEvent();
	themeFilterEvent.setFilterArray(themeArray);
	EventBus.getDefault().post(themeFilterEvent);
    }

}
