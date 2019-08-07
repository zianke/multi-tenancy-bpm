package org.activiti;

import javax.persistence.Entity;


public class Order {

    private String time;

    private String type;


    public Order() {

    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
