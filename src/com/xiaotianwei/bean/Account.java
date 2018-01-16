package com.xiaotianwei.bean;

public class Account {
    private int id;
    private String accountname;
    private int price;
    private int pic;

    public Account(int id, String accountname, int price, int pic) {
        this.id = id;
        this.accountname = accountname;
        this.price = price;
        this.pic = pic;
    }

    public Account(String accountname, int price, int pic) {
        this.accountname = accountname;
        this.price = price;
        this.pic = pic;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
