package com.sk.lgdx.module.study.network.response;

import com.sk.lgdx.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/1/3.
 */

public class SearchResultObj extends BaseObj {


    private List<CourseWareBean> CourseWare;

    public List<CourseWareBean> getCourseWare() {
        return CourseWare;
    }

    public void setCourseWare(List<CourseWareBean> CourseWare) {
        this.CourseWare = CourseWare;
    }

    public static class CourseWareBean {
        /**
         * courseware_id : 3
         * image_url : http://121.40.186.118:1554/upload/kejian3.png
         * title : 小葵花爸爸课堂开课啦
         * video_pdf : http://121.40.186.118:1554/upload/201712/22/201712221005353985.mp4
         * keynote_speaker : 
         * add_time : 2017-12-9
         * sales : 13
         * thumbup_count : 0
         * courseware_record_count : 2
         */

        private String courseware_id;
        private String image_url;
        private String title;
        private String video_pdf;
        private String keynote_speaker;
        private String add_time;
        private String sales;
        private String thumbup_count;
        private String courseware_record_count;

        public String getCourseware_id() {
            return courseware_id;
        }

        public void setCourseware_id(String courseware_id) {
            this.courseware_id = courseware_id;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVideo_pdf() {
            return video_pdf;
        }

        public void setVideo_pdf(String video_pdf) {
            this.video_pdf = video_pdf;
        }

        public String getKeynote_speaker() {
            return keynote_speaker;
        }

        public void setKeynote_speaker(String keynote_speaker) {
            this.keynote_speaker = keynote_speaker;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getThumbup_count() {
            return thumbup_count;
        }

        public void setThumbup_count(String thumbup_count) {
            this.thumbup_count = thumbup_count;
        }

        public String getCourseware_record_count() {
            return courseware_record_count;
        }

        public void setCourseware_record_count(String courseware_record_count) {
            this.courseware_record_count = courseware_record_count;
        }
    }
}
