package com.medisook.app;

public class DrugItem {
    String drugName;
    String drugImg;

    public DrugItem(String drugName){
        this.drugName = drugName;
    }
    public String getDrugName(){
        return drugName;
    }
    public void setDrugName(String drugName){
        this.drugName = drugName;
    }
}
