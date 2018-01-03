package com.sk.lgdx.module.home.network.response;

import java.util.List;

/**
 * Created by Administrator on 2017/12/28.
 */

public class KaoqinObj {

    /**
     * time : 12-25
     * weekday : 1
     * time_hour : [{"course_id":1,"content":"高等数学A-301(魏老师)","time":1,"is_qiandao":1},{"course_id":0,"content":"","time":2,"is_qiandao":1},{"course_id":2,"content":"毛概A-301(魏老师)","time":3,"is_qiandao":1},{"course_id":0,"content":"","time":4,"is_qiandao":1}]
     */

    private String time;
    private String weekday;
    private List<TimeHourBean> time_hour;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public List<TimeHourBean> getTime_hour() {
        return time_hour;
    }

    public void setTime_hour(List<TimeHourBean> time_hour) {
        this.time_hour = time_hour;
    }

    public static class TimeHourBean {
        /**
         * course_id : 1
         * content : 高等数学A-301(魏老师)
         * time : 1
         * is_qiandao : 1
         */

        private String course_id;
        private String content;
        private String time;
        private String is_qiandao;

        public String getCourse_id() {
            return course_id;
        }

        public void setCourse_id(String course_id) {
            this.course_id = course_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getIs_qiandao() {
            return is_qiandao;
        }

        public void setIs_qiandao(String is_qiandao) {
            this.is_qiandao = is_qiandao;
        }
    }
}
