package com.example.listycity;

import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class ListItem {
    private String city;
    private String province;

    public ListItem(String city, String province) {
        this.city = city;
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
