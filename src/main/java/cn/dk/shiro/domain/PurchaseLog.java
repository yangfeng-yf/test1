package cn.dk.shiro.domain;

import java.util.Date;

public class PurchaseLog {
    private Integer countId;

    private Integer aId;

    private Integer num;

    private Date lastentertime;

    private Double price;

    private Integer requestPeppleId;

    private Integer pStatus;

    private Integer approverId;

    public Integer getCountId() {
        return countId;
    }

    public void setCountId(Integer countId) {
        this.countId = countId;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getLastentertime() {
        return lastentertime;
    }

    public void setLastentertime(Date lastentertime) {
        this.lastentertime = lastentertime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getRequestPeppleId() {
        return requestPeppleId;
    }

    public void setRequestPeppleId(Integer requestPeppleId) {
        this.requestPeppleId = requestPeppleId;
    }

    public Integer getpStatus() {
        return pStatus;
    }

    public void setpStatus(Integer pStatus) {
        this.pStatus = pStatus;
    }

    public Integer getApproverId() {
        return approverId;
    }

    public void setApproverId(Integer approverId) {
        this.approverId = approverId;
    }
}