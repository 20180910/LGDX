package com.sk.lgdx.module.home.network.response;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public class StudentOperationListObj {

    /**
     * add_time : 12月18日作业
     * operation_list : [{"operation_id":5,"title":"管理系统","end_time":"12月22日","is_submit":1,"operation_submit":[{"content":"啦啦啦啦","operation_image":["http://121.40.186.118:1554/upload/tijiaozuoye.png","http://121.40.186.118:1554/upload/tijiaozuoye.png","http://121.40.186.118:1554/upload/tijiaozuoye.png"]}]}]
     */

    private String add_time;
    private List<OperationListBean> operation_list;

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public List<OperationListBean> getOperation_list() {
        return operation_list;
    }

    public void setOperation_list(List<OperationListBean> operation_list) {
        this.operation_list = operation_list;
    }

    public static class OperationListBean {
        /**
         * operation_id : 5
         * title : 管理系统
         * end_time : 12月22日
         * is_submit : 1
         * operation_submit : [{"content":"啦啦啦啦","operation_image":["http://121.40.186.118:1554/upload/tijiaozuoye.png","http://121.40.186.118:1554/upload/tijiaozuoye.png","http://121.40.186.118:1554/upload/tijiaozuoye.png"]}]
         */

        private int operation_id;
        private String title;
        private String end_time;
        private int is_submit;
        private List<OperationSubmitBean> operation_submit;

        public int getOperation_id() {
            return operation_id;
        }

        public void setOperation_id(int operation_id) {
            this.operation_id = operation_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public int getIs_submit() {
            return is_submit;
        }

        public void setIs_submit(int is_submit) {
            this.is_submit = is_submit;
        }

        public List<OperationSubmitBean> getOperation_submit() {
            return operation_submit;
        }

        public void setOperation_submit(List<OperationSubmitBean> operation_submit) {
            this.operation_submit = operation_submit;
        }

        public static class OperationSubmitBean {
            /**
             * content : 啦啦啦啦
             * operation_image : ["http://121.40.186.118:1554/upload/tijiaozuoye.png","http://121.40.186.118:1554/upload/tijiaozuoye.png","http://121.40.186.118:1554/upload/tijiaozuoye.png"]
             */

            private String content;
            private List<String> operation_image;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public List<String> getOperation_image() {
                return operation_image;
            }

            public void setOperation_image(List<String> operation_image) {
                this.operation_image = operation_image;
            }
        }
    }
}
