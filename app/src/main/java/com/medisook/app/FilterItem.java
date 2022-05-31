package com.medisook.app;


public class FilterItem {
    String filterName;
    private boolean isSelected;

    public FilterItem(String filterName, boolean isSelected){
        this.filterName = filterName;
        this.isSelected = isSelected;
    }

    public FilterItem(String filterName) {
        this.filterName = filterName;
    }
    public String getFilterName() {
        return filterName;
    }
    public void setFilterName(String filterName){
        this.filterName = filterName;
    }
    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
