package com.sk.lgdx.module.home.event;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/8.
 */

public class TijiaozuoyeEvent implements Serializable {
    public  String operation_id;
    public TijiaozuoyeEvent(String operation_id){
        this.operation_id=operation_id;

    }
}
