package com.gastawny.shockwave.actions;

import java.util.Map;

public class ActionRequest {
    private String action;
    private Map<String, Object> data;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Object getDataClass() {
        return data.getClass();
    }
}
