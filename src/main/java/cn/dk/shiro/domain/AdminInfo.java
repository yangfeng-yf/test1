package cn.dk.shiro.domain;

import java.util.Date;

public class AdminInfo {
    private Integer adId;

    private Long adType;

    private String adPhone;

    private String adPassword;

    private Date adLastlogintime;

    private String adName;

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public Long getAdType() {
        return adType;
    }

    public void setAdType(Long adType) {
        this.adType = adType;
    }

    public String getAdPhone() {
        return adPhone;
    }

    public void setAdPhone(String adPhone) {
        this.adPhone = adPhone == null ? null : adPhone.trim();
    }

    public String getAdPassword() {
        return adPassword;
    }

    public void setAdPassword(String adPassword) {
        this.adPassword = adPassword == null ? null : adPassword.trim();
    }

    public Date getAdLastlogintime() {
        return adLastlogintime;
    }

    public void setAdLastlogintime(Date adLastlogintime) {
        this.adLastlogintime = adLastlogintime;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName == null ? null : adName.trim();
    }
}