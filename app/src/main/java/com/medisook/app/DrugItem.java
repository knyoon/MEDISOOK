package com.medisook.app;

import java.io.Serializable;

public class DrugItem implements Serializable {
    String drugName;
    String drugImg;

//        private String member_name;
//        private String member_entp;
//        private String member_image;
//        public String getMember_id() {
//            return member_name;
//        }
//        public String getMember_name() {
//            return member_entp;
//        }
//        public String getMember_image() {
//            return member_image;
//        }
//        public void setMember_id(String member_id) {
//            this.member_name = member_id;
//        }
//        public void setMember_name(String member_name) {
//            this.member_entp = member_name;
//        }
//        public void setMember_image(String member_address) {
//            this.member_image = member_image;
//        }
    public DrugItem(){}
    public DrugItem(String drugName){}
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
    }

