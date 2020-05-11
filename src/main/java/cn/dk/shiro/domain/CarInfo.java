package cn.dk.shiro.domain;

import java.util.Date;

public class CarInfo {
    private Integer countId;

    private String cType;

    private String cBrand;

    private String cPerson;

    private Date cCreatetime;



    public Integer getCountId() {
        return countId;
    }

    public void setCountId(Integer countId) {
        this.countId = countId;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public String getcBrand() {
        return cBrand;
    }

    public void setcBrand(String cBrand) {
        this.cBrand = cBrand;
    }

    public String getcPerson() {
        return cPerson;
    }

    public void setcPerson(String cPerson) {
        this.cPerson = cPerson;
    }

    public Date getcCreatetime() {
        return cCreatetime;
    }

    public void setcCreatetime(Date cCreatetime) {
        this.cCreatetime = cCreatetime;
    }
}