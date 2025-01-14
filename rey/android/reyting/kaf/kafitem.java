package com.bbstudios.reyting.kaf;

public class kafitem {
    String  kid,kady,fady,bal;

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getKady() {
        return kady;
    }

    public void setKady(String kady) {
        this.kady = kady;
    }

    public String getFady() {
        return fady;
    }

    public void setFady(String fady) {
        this.fady = fady;
    }

    public String getBal() {
        return bal;
    }

    public void setBal(String bal) {
        this.bal = bal;
    }

    public kafitem(String kid, String kady, String fady, String bal) {
        this.kid = kid;
        this.kady = kady;
        this.fady = fady;
        this.bal = bal;
    }
}
