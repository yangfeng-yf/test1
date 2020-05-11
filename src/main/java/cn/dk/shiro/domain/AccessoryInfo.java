package cn.dk.shiro.domain;

import java.util.Date;

public class AccessoryInfo {
    private Integer aId;

    private String aName;

    private Integer aNum;

    private Integer aSId;

    private String aDescribe;

    private Date aLastSupplyTime;

    private Double aSalePrice;

    private Double aIncomingPrice;

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName == null ? null : aName.trim();
    }

    public Integer getaNum() {
        return aNum;
    }

    public void setaNum(Integer aNum) {
        this.aNum = aNum;
    }

    public Integer getaSId() {
        return aSId;
    }

    public void setaSId(Integer aSId) {
        this.aSId = aSId;
    }

    public String getaDescribe() {
        return aDescribe;
    }

    public void setaDescribe(String aDescribe) {
        this.aDescribe = aDescribe == null ? null : aDescribe.trim();
    }

    public Date getaLastSupplyTime() {
        return aLastSupplyTime;
    }

    public void setaLastSupplyTime(Date aLastSupplyTime) {
        this.aLastSupplyTime = aLastSupplyTime;
    }

    public Double getaSalePrice() {
        return aSalePrice;
    }

    public void setaSalePrice(Double aSalePrice) {
        this.aSalePrice = aSalePrice;
    }

    public Double getaIncomingPrice() {
        return aIncomingPrice;
    }

    public void setaIncomingPrice(Double aIncomingPrice) {
        this.aIncomingPrice = aIncomingPrice;
    }
}