package com.medisook.app;

import java.io.Serializable;

public class DrugItem implements Serializable {
    String drugName;
    String drugImg;
    String drugentp;
    String drugcode;
    String qnt;
    String otc;
    String chart;
    String efcy;
    String classname;
    String usemethod;
    String qesitm;
    String term;
    String deposit;
    String totalcontent;
    String mainingr;
    String ingrname;

    public DrugItem() {
    }

    public DrugItem(String drugName) {
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugImg() {
        return drugImg;
    }

    public void setDrugImg(String drugImg) {
        this.drugImg = drugImg;
    }

    public String getDrugentp() {
        return drugentp;
    }

    public void setDrugentp(String drugentp) {
        this.drugentp = drugentp;
    }

    public String getDrugcode() {
        return drugcode;
    }

    public void setDrugcode(String drugcode) {
        this.drugcode = drugcode;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getQnt() {
        return qnt;
    }

    public void setQnt(String qnt) {
        this.qnt = qnt;
    }

    public String getOtc() {
        return otc;
    }

    public void setOtc(String otc) {
        this.otc = otc;
    }

    public String getChart() {
        return chart;
    }

    public void setChart(String chart) {
        this.chart = chart;
    }

    public String getEfcy() {
        return efcy;
    }

    public void setEfcy(String efcy) {
        this.efcy = efcy;
    }

    public String getUsemethod() {
        return usemethod;
    }

    public void setUsemethod(String usemethod) {
        this.usemethod = usemethod;
    }

    public String getQesitm() {
        return qesitm;
    }

    public void setQesitm(String qesitm) {
        this.qesitm = qesitm;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTotalcontent() {
        return totalcontent;
    }

    public void setTotalcontent(String totalcontent) {
        this.totalcontent = totalcontent;
    }

    public String getMainingr() {
        return mainingr;
    }

    public void setMainingr(String mainingr) {
        this.mainingr = mainingr;
    }

    public String getIngrname() {
        return ingrname;
    }

    public void setIngrname(String ingrname) {
        this.ingrname = ingrname;
    }


}

