package com.sk.lgdx.module.home.network.response;

import com.sk.lgdx.base.BaseObj;

/**
 * Created by Administrator on 2018/1/2.
 */

public class NextLessonObj extends BaseObj {


    /**
     * title : 高等数学A-302(魏老师)
     * add_time : 周二10:00
     */

    private String title;
    private String add_time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}
