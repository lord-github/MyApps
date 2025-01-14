package com.bbstudios.reyting.poisk;

public class poitem {
    String id,kk,ady;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKk() {
        return kk;
    }

    public void setKk(String kk) {
        this.kk = kk;
    }

    public String getAdy() {
        return ady;
    }

    public void setAdy(String ady) {
        this.ady = ady;
    }

    public poitem(String id, String kk, String ady) {
        this.id = id;
        this.kk = kk;
        this.ady = ady;
    }
}
