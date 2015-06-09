package com.yizi.iwuse.filter.model;

import java.util.ArrayList;

/**
 * 产品过滤item
 * 
 * @author hehaodong
 *
 */
public class ProductFilterItem {

    private ArrayList<String> filterArray;
    private String filterName;

    public ArrayList<String> getFilterArray() {
	return filterArray;
    }

    public void setFilterArray(ArrayList<String> filterArray) {
	this.filterArray = filterArray;
    }

    public String getFilterName() {
	return filterName;
    }

    public void setFilterName(String filterName) {
	this.filterName = filterName;
    }

    public ProductFilterItem(String filterName, ArrayList<String> filterArray) {
	this.filterName = filterName;
	this.filterArray = filterArray;
    }

}
