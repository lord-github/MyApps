package com.bbstudios.ecash.optional;

public class statItem {
    String ady,sene;
    Integer backra;

    public String getAdy() {
        return ady;
    }

    public void setAdy(String ady) {
        this.ady = ady;
    }

    public String getSene() {
        return sene;
    }

    public void setSene(String sene) {
        this.sene = sene;
    }

    public Integer getBackra() {
        return backra;
    }

    public void setBackra(Integer backra) {
        this.backra = backra;
    }

    public statItem(String ady, String sene, Integer backra) {
        this.ady = ady;
        this.sene = sene;
        this.backra = backra;
    }
}
