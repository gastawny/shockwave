package com.gastawny.shockwave.shared.handlers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HandlerReq {
    private String type;
    private Object data;

    public Object getDataClass() {
        return data.getClass();
    }
}
