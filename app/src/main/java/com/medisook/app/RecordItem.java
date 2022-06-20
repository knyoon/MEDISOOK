package com.medisook.app;

import java.io.Serializable;

public class RecordItem implements Serializable {
    String drugName;
    String drugImg;
    private String member_name;
    private String member_entp;
    private String member_image;
    String tag1;
    String tag2;
    String tag3;
    String goodbad;
    String date1;
    String date2;
    public String getMember_id() {
        return member_name;
    }
    public String getMember_name() {
        return member_entp;
    }
    public String getMember_image() {
        return member_image;
    }
    public void setMember_id(String member_id) {
        this.member_name = member_id;
    }
    public void setMember_name(String member_name) {
        this.member_entp = member_name;
    }
    public void setMember_image(String member_address) {
        this.member_image = member_image;
    }
    public RecordItem(){}
    public RecordItem(String drugName){}
    public String getDrugName(){
        return drugName;
    }
    public void setDrugName(String drugName){
        this.drugName = drugName;
    }
    public String getDrugImg(){
        return drugImg;
    }
    public void setDrugImg(String drugImg){
        this.drugImg = drugImg;
    }
    public void setTag1(String tag1){this.tag1=tag1;}
    public String getTag1(){return tag1;}
    public void setTag2(String tag2){this.tag2=tag2;}
    public String getTag2(){return tag2;}
    public void setTag3(String tag3){this.tag3=tag3;}
    public String getTag3(){return tag3;}
    public void setGoodbad(String goodbad){this.goodbad=goodbad;}
    public String getGoodbad(){return goodbad;}
    public void setDate1(String date1){this.date1=date1;}
    public String getDate1(){return date1;}
    public void setDate2(String date2){this.date2=date2;}
    public String getDate2(){return date2;}
}