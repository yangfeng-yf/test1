package cn.dk.shiro.domain;

import java.util.Date;

public class UseAccessoriesLog {
    private Integer countId;

    private Integer cCountid;

    private String accessory;

    private Integer num;

    private String username;

    private Date applicationTime;

    private Integer getType;

    private Double price;

    public Integer getCountId() {
        return countId;
    }

    public void setCountId(Integer countId) {
        this.countId = countId;
    }

    public Integer getcCountid() {
        return cCountid;
    }

    public void setcCountid(Integer cCountid) {
        this.cCountid = cCountid;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public Integer getGetType() {
        return getType;
    }

    public void setGetType(Integer getType) {
        this.getType = getType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}