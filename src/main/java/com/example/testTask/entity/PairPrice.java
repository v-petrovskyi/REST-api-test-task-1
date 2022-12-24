package com.example.testTask.entity;


public class PairPrice {
    private String curr1;
    private String curr2;
    private String lprice;

    public PairPrice() {
    }

    public PairPrice(String curr1, String curr2, String lprice) {
        this.curr1 = curr1;
        this.curr2 = curr2;
        this.lprice = lprice;
    }

    public String getCurr1() {
        return curr1;
    }

    public void setCurr1(String curr1) {
        this.curr1 = curr1;
    }

    public String getCurr2() {
        return curr2;
    }

    public void setCurr2(String curr2) {
        this.curr2 = curr2;
    }

    public String getLprice() {
        return lprice;
    }

    public void setLprice(String lprice) {
        this.lprice = lprice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PairPrice{");
        sb.append("curr1='").append(curr1).append('\'');
        sb.append(", curr2='").append(curr2).append('\'');
        sb.append(", lprice='").append(lprice).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
