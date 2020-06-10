package com.jeff.entity;

public class LinkMan {

    private Integer lkn_id;

    private String lkn_name;

    private String lkn_gender;

    private String lkn_phone;

    // 在聯繫人實體類裡面表示所屬客戶，一個聯繫人只能屬於一個客戶
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getLkn_id() {
        return lkn_id;
    }

    public void setLkn_id(Integer lkn_id) {
        this.lkn_id = lkn_id;
    }

    public String getLkn_name() {
        return lkn_name;
    }

    public void setLkn_name(String lkn_name) {
        this.lkn_name = lkn_name;
    }

    public String getLkn_gender() {
        return lkn_gender;
    }

    public void setLkn_gender(String lkn_gender) {
        this.lkn_gender = lkn_gender;
    }

    public String getLkn_phone() {
        return lkn_phone;
    }

    public void setLkn_phone(String lkn_phone) {
        this.lkn_phone = lkn_phone;
    }
}
