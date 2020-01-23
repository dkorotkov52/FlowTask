package com.korotkov.core;

public class Arc {
    private int id1;
    private int id2;
    private int fWeigth;

    public Arc(int id1, int id2, int fWeigth)
    {
        this.id1 = id1;
        this.id2 = id2;
        this.fWeigth = fWeigth;
    }

    public int getfWeigth() {
        return fWeigth;
    }

    public void setfWeigth(int fWeigth) {
        this.fWeigth = fWeigth;
    }

    public int getId2() {
        return id2;
    }
}
