package com.sk.lgdx.module.study.network.response;

import com.sk.lgdx.base.BaseObj;

/**
 * Created by Administrator on 2017/12/20.
 */

public class MajorTypeObj extends BaseObj {

    /**
     * course_type_id : 0
     * course_type_name : 全部
     */

    private String course_type_id;
    private String course_type_name;

    public String getCourse_type_id() {
        return course_type_id;
    }

    public void setCourse_type_id(String course_type_id) {
        this.course_type_id = course_type_id;
    }

    public String getCourse_type_name() {
        return course_type_name;
    }

    public void setCourse_type_name(String course_type_name) {
        this.course_type_name = course_type_name;
    }
}
