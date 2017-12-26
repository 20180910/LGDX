package com.sk.lgdx.module.home.network.request;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public class QuerentijiaoBody {


    /**
     * content : sample string 1
     * image : [{"images":"sample string 1"},{"images":"sample string 1"}]
     */

    private String content;
    private List<ImageBean> image;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ImageBean> getImage() {
        return image;
    }

    public void setImage(List<ImageBean> image) {
        this.image = image;
    }

    public static class ImageBean {
        /**
         * images : sample string 1
         */

        private String images;

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }
    }
}
