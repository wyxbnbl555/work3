package com.kuang.work3;

import java.sql.Date;

public class bigOrder {
    private int o_id;
    private Date o_time;
    private int p_id;
    private String p_name;
    private double p_price;

    public bigOrder() {
    }

    public bigOrder(int o_id, Date o_time, int p_id, String p_name, double p_price) {
        this.o_id = o_id;
        this.o_time = o_time;
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_price = p_price;
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public Date getO_time() {
        return o_time;
    }

    public void setO_time(Date o_time) {
        this.o_time = o_time;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public double getP_price() {
        return p_price;
    }

    public void setP_price(double p_price) {
        this.p_price = p_price;
    }

    @Override
    public String toString() {
        return "bigOrder{" +
                "o_id=" + o_id +
                ", o_time=" + o_time +
                ", p_id=" + p_id +
                ", p_name='" + p_name + '\'' +
                ", p_price=" + p_price +
                '}';
    }
}

