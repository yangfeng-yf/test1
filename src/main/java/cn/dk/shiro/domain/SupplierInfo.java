package cn.dk.shiro.domain;

public class    SupplierInfo {
    private Integer sId;

    private String sCampanyname;

    private String sAddress;

    private String sPhone;

    private String sPersonLiable;

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getsCampanyname() {
        return sCampanyname;
    }

    public void setsCampanyname(String sCampanyname) {
        this.sCampanyname = sCampanyname == null ? null : sCampanyname.trim();
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress == null ? null : sAddress.trim();
    }

    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone == null ? null : sPhone.trim();
    }

    public String getsPersonLiable() {
        return sPersonLiable;
    }

    public void setsPersonLiable(String sPersonLiable) {
        this.sPersonLiable = sPersonLiable == null ? null : sPersonLiable.trim();
    }
}