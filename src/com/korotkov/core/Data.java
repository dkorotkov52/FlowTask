package com.korotkov.core;

public class Data {
    private int n;
    private int m;
    private int tick;
    private int[] maxProd;
    private int[][] maxProdPT;
    private int[][] goalProd;
    private int[][] suppliers;

    public Data(int n, int m, int tick, int[] maxProd, int[][] maxProdPT, int[][] goalProd, int[][] suppliers) {
        this.n = n;
        this.m = m;
        this.tick = tick;
        this.maxProd = maxProd;
        this.maxProdPT = maxProdPT;
        this.goalProd = goalProd;
        this.suppliers = suppliers;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public int getTick() {
        return tick;
    }

    public int[] getMaxProd() {
        return maxProd;
    }

    public int[][] getGoalProd() {
        return goalProd;
    }

    public int[][] getMaxProdPT() {
        return maxProdPT;
    }

    public int[][] getSuppliers() {
        return suppliers;
    }
}
