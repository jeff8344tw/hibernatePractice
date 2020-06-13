package com.jeff.entity;

import java.util.HashSet;
import java.util.Set;

public class Customer {

    private Integer cid;

    private String custName;

    private String custLevel;

    private String custSource;

    private String custPhone;

    private String custMobile;

    // 在客戶實體類裡面表示多個聯繫人，一個客戶有多個聯繫人
    // hibernate要求使用集合表示多個，使用set集合
    private Set<LinkMan> setLinkMan = new HashSet<>();

    public Set<LinkMan> getSetLinkMan() {
        return setLinkMan;
    }

    public void setSetLinkMan(Set<LinkMan> setLinkMan) {
        this.setLinkMan = setLinkMan;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(String custMobile) {
        this.custMobile = custMobile;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cid=" + cid +
                ", custName='" + custName + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custMobile='" + custMobile + '\'' +
                ", setLinkMan=" + setLinkMan +
                '}';
    }
}
