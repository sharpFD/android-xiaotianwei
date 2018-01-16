package com.xiaotianwei.bean;

public class Record {
    String type;   //收入支出类型
    String content;//做了什么事情
    String date; //日期
    String month;
    String money;//用了多少钱
    int id;
    String accountname;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public Record(String type, String content, String date, String money, int id,String accountname,String month) {
        this.type = type;
        this.content = content;
        this.date = date;
        this.money = money;
        this.id=id;
        this.accountname=accountname;
        this.month=month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Record(){}

    public Record(String type, String content, String date, String money,String accountname,String month) {
        this.type = type;
        this.content = content;
        this.date = date;
        this.money = money;
        this.accountname=accountname;
        this.month=month;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
