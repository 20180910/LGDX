package com.sk.lgdx.module.home.network.response;

import com.sk.lgdx.base.BaseObj;

/**
 * Created by Administrator on 2017/12/21.
 */

public class NewsDetailObj extends BaseObj {


    /**
     * id : 5066
     * title : 作业提交提醒
     * content : 毛概作业截止到12月28号提交哦
     * add_time : 2017/12/20
     */

    private String id;
    private String title;
    private String content;
    private String add_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}
