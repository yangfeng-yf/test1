package cn.dk.shiro.domain;

import java.util.Date;

public class MaintenanceRegistrationLog {
    private Integer mId;

    private Integer mCCountId;

    private String mUser;

    private String mSalesman;

    private String mService;

    private Integer mStuta;

    private Date mRegiterTime;

    private Date mStartTime;

    private Date mEndTime;

    private String mRemark;

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public Integer getmCCountId() {
        return mCCountId;
    }

    public void setmCCountId(Integer mCCountId) {
        this.mCCountId = mCCountId;
    }

    public String getmUser() {
        return mUser;
    }

    public void setmUser(String mUser) {
        this.mUser = mUser;
    }

    public String getmSalesman() {
        return mSalesman;
    }

    public void setmSalesman(String mSalesman) {
        this.mSalesman = mSalesman;
    }

    public String getmService() {
        return mService;
    }

    public void setmService(String mService) {
        this.mService = mService;
    }

    public Integer getmStuta() {
        return mStuta;
    }

    public void setmStuta(Integer mStuta) {
        this.mStuta = mStuta;
    }

    public Date getmRegiterTime() {
        return mRegiterTime;
    }

    public void setmRegiterTime(Date mRegiterTime) {
        this.mRegiterTime = mRegiterTime;
    }

    public Date getmStartTime() {
        return mStartTime;
    }

    public void setmStartTime(Date mStartTime) {
        this.mStartTime = mStartTime;
    }

    public Date getmEndTime() {
        return mEndTime;
    }

    public void setmEndTime(Date mEndTime) {
        this.mEndTime = mEndTime;
    }

    public String getmRemark() {
        return mRemark;
    }

    public void setmRemark(String mRemark) {
        this.mRemark = mRemark;
    }
}