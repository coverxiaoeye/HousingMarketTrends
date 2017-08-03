package com.xiao.housingmarket.beans;

import java.util.Date;

/**
 * Created by wanyg on 17/8/3.
 */
public class SaleStats {
    private String zone;
    private double area;
    private int housing_num;
    private double housing_area;

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getHousing_num() {
        return housing_num;
    }

    public void setHousing_num(int housing_num) {
        this.housing_num = housing_num;
    }

    public double getHousing_area() {
        return housing_area;
    }

    public void setHousing_area(double housing_area) {
        this.housing_area = housing_area;
    }
}
