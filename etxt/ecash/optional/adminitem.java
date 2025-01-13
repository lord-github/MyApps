package com.bbstudios.ecash.optional;

public class adminitem {
    String zaid,userid,yeryetady,zaAdy;
    Integer renk;

    public String getZaid() {
        return zaid;
    }

    public void setZaid(String zaid) {
        this.zaid = zaid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getYeryetady() {
        return yeryetady;
    }

    public void setYeryetady(String yeryetady) {
        this.yeryetady = yeryetady;
    }

    public String getZaAdy() {
        return zaAdy;
    }

    public void setZaAdy(String zaAdy) {
        this.zaAdy = zaAdy;
    }

    public Integer getRenk() {
        return renk;
    }

    public void setRenk(Integer renk) {
        this.renk = renk;
    }

    public adminitem(String zaid, String userid, String yeryetady, String zaAdy, Integer renk) {
        this.zaid = zaid;
        this.userid = userid;
        this.yeryetady = yeryetady;
        this.zaAdy = zaAdy;
        this.renk = renk;
    }
}
