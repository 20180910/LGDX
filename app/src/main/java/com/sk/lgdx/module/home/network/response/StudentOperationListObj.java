package com.sk.lgdx.module.home.network.response;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public class StudentOperationListObj {


    /**
     * add_time : 今日作业
     * operation_list : [{"operation_id":17,"title":"毛概理论","end_time":"1月13日","is_submit":1,"operation_submit":[{"operation_submit_id":47,"content":"啦啦啦啦啦啦啦","operation_image":[{"id":44,"images":"http://121.40.186.118:1554/upload/201801/10/2017-12-16-04-17-59.txt"},{"id":45,"images":"http://121.40.186.118:1554/upload/201801/10/2017-12-16-04-17-58.txt"},{"id":46,"images":"http://121.40.186.118:1554/upload/201801/10/c1260e371530889eb52a4d4b75188a1b.jpg"}]}]}]
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
         * operation_id : 17
         * title : 毛概理论
         * end_time : 1月13日
         * is_submit : 1
         * operation_submit : [{"operation_submit_id":47,"content":"啦啦啦啦啦啦啦","operation_image":[{"id":44,"images":"http://121.40.186.118:1554/upload/201801/10/2017-12-16-04-17-59.txt"},{"id":45,"images":"http://121.40.186.118:1554/upload/201801/10/2017-12-16-04-17-58.txt"},{"id":46,"images":"http://121.40.186.118:1554/upload/201801/10/c1260e371530889eb52a4d4b75188a1b.jpg"}]}]
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
             * operation_submit_id : 47
             * content : 啦啦啦啦啦啦啦
             * operation_image : [{"id":44,"images":"http://121.40.186.118:1554/upload/201801/10/2017-12-16-04-17-59.txt"},{"id":45,"images":"http://121.40.186.118:1554/upload/201801/10/2017-12-16-04-17-58.txt"},{"id":46,"images":"http://121.40.186.118:1554/upload/201801/10/c1260e371530889eb52a4d4b75188a1b.jpg"}]
             */

            private int operation_submit_id;
            private String content;
            private List<OperationImageBean> operation_image;

            public int getOperation_submit_id() {
                return operation_submit_id;
            }

            public void setOperation_submit_id(int operation_submit_id) {
                this.operation_submit_id = operation_submit_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public List<OperationImageBean> getOperation_image() {
                return operation_image;
            }

            public void setOperation_image(List<OperationImageBean> operation_image) {
                this.operation_image = operation_image;
            }

            public static class OperationImageBean {
                /**
                 * id : 44
                 * images : http://121.40.186.118:1554/upload/201801/10/2017-12-16-04-17-59.txt
                 */

                private int id;
                private String images;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getImages() {
                    return images;
                }

                public void setImages(String images) {
                    this.images = images;
                }
            }
        }
    }
}
