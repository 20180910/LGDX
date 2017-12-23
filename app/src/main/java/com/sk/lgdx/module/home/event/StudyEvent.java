package com.sk.lgdx.module.home.event;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/8.
 */

public class StudyEvent implements Serializable {
    public  String type;
    public StudyEvent(String type){
        this.type=type;

    }
}
