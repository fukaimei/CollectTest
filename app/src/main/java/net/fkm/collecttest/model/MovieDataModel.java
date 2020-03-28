package net.fkm.collecttest.model;


import java.util.List;

public class MovieDataModel extends BaseModel {


    /**
     * reason : 成功的返回
     * data : [{"name":"大赢家2020","tag":"大陆，2020","updateStatus":"评分：6.8","mvid":"19af39277b","video_type":"movie","poster":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2591844862.webp"},{"name":"决战中途岛","tag":"美国/大陆，2019","updateStatus":"评分：7.7","mvid":"224d60ab10","video_type":"movie","poster":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2573582192.webp"},{"name":"叶问4完结篇","tag":"香港，2019","updateStatus":"评分：7.2","mvid":"c1a7f47a45","video_type":"movie","poster":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2577437186.webp"},{"name":"囧妈","tag":"大陆，2020","updateStatus":"评分：5.9","mvid":"dcfefbe152","video_type":"movie","poster":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2581835383.webp"},{"name":"我和我的祖国","tag":"大陆，2019","updateStatus":"评分：7.8","mvid":"","video_type":"movie","poster":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2567998580.webp"},{"name":"中国机长","tag":"大陆，2019","updateStatus":"评分：7.0","mvid":"OCh3Vq35","video_type":"movie","poster":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2568258113.webp"},{"name":"双子杀手","tag":"美国，2019","updateStatus":"评分：7.1","mvid":"23gmG6ES","video_type":"movie","poster":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2570906505.webp"},{"name":"哪吒之魔童降世","tag":"大陆，2019","updateStatus":"评分：8.5","mvid":"0c84dfc2cf","video_type":"movie","poster":"https://img1.doubanio.com/view/photo/m/public/p2565106098.webp"},{"name":"雪人奇缘","tag":"美国 大陆，2019","updateStatus":"评分：7.6","mvid":"r9lChb5A","video_type":"movie","poster":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2567925521.webp"},{"name":"三千鸦杀","tag":"大陆，2020","updateStatus":"更新中","mvid":"beb0c112ee","video_type":"episode","poster":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2591521419.webp"},{"name":"九州天空城2","tag":"大陆，2020","updateStatus":"更新中","mvid":"eb61d82fca","video_type":"episode","poster":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2591525655.webp"},{"name":"无心法师3","tag":"大陆，2020","updateStatus":"更新中","mvid":"3121aec797","video_type":"episode","poster":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2589120708.webp"},{"name":"鬓边不是海棠红","tag":"大陆，2020","updateStatus":"更新中","mvid":"","video_type":"episode","poster":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2558687872.webp"},{"name":"我在北京等你","tag":"大陆，2020","updateStatus":"更新中","mvid":"","video_type":"episode","poster":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2587487174.webp"},{"name":"安家","tag":"大陆，2020","updateStatus":"53集全","mvid":"","video_type":"episode","poster":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2587877550.webp"},{"name":"完美关系","tag":"大陆，2020","updateStatus":"51集全","mvid":"","video_type":"episode","poster":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2586318978.webp"},{"name":"上古密约","tag":"大陆，2020","updateStatus":"45集全","mvid":"","video_type":"episode","poster":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2583854519.webp"},{"name":"庆余年","tag":"大陆，2019","updateStatus":"46集全","mvid":"kZlPMQuz","video_type":"episode","poster":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2575362797.webp"},{"name":"绝命响应","tag":"大陆，2019","updateStatus":"更新中","mvid":"","video_type":"episode","poster":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2552380414.webp"},{"name":"地灵曲","tag":"大陆，2017","updateStatus":"更新中","mvid":"","video_type":"episode","poster":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2499636952.webp"},{"name":"天行九歌","tag":"大陆，2016","updateStatus":"更新中","mvid":"","video_type":"episode","poster":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2563882358.webp"},{"name":"万界仙踪第2季","tag":"大陆，2019","updateStatus":"更新中","mvid":"755e4a83ca","video_type":"episode","poster":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2555465808.webp"},{"name":"书灵记","tag":"大陆，2019","updateStatus":"更新中","mvid":"","video_type":"episode","poster":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2568513156.webp"},{"name":"斗罗大陆","tag":"大陆，2018","updateStatus":"更新中","mvid":"70c01dc23c","video_type":"episode","poster":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2510966013.webp"},{"name":"墓王之王之幽都战","tag":"大陆，2019","updateStatus":"更新中","mvid":"","video_type":"episode","poster":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2578531371.webp"},{"name":"西行纪","tag":"大陆，2018","updateStatus":"更新中","mvid":"","video_type":"episode","poster":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2528170177.webp"},{"name":"万国志","tag":"大陆，2019","updateStatus":"更新中","mvid":"","video_type":"episode","poster":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2543555882.webp"},{"name":"声临其境 第三季","tag":"大陆，2019","updateStatus":"更新中","mvid":"","video_type":"movie","poster":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2580565307.webp"},{"name":"上新了·故宫第2季","tag":"大陆，2019","updateStatus":"更新中","mvid":"GZYb4C5Y","video_type":"movie","poster":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2574793725.webp"},{"name":"青春有你第2季","tag":"大陆，2020","updateStatus":"更新中","mvid":"","video_type":"movie","poster":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2587839690.webp"},{"name":"朋友请听好","tag":"大陆，2020","updateStatus":"更新中","mvid":"","video_type":"movie","poster":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2585215551.webp"},{"name":"天赐的声音","tag":"大陆，2020","updateStatus":"更新中","mvid":"","video_type":"movie","poster":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2581829564.webp"},{"name":"歌手·当打之年","tag":"大陆，2020","updateStatus":"更新中","mvid":"","video_type":"movie","poster":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2579588197.webp"},{"name":"明星大侦探 第五季","tag":"大陆，2019","updateStatus":"更新中","mvid":"2B6F2mJQ","video_type":"movie","poster":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2572359178.webp"},{"name":"了不起的长城","tag":"大陆，2020","updateStatus":"更新中","mvid":"","video_type":"movie","poster":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2579433708.webp"},{"name":"王牌对王牌 第5季","tag":"大陆，2020","updateStatus":"更新中","mvid":"","video_type":"movie","poster":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2581829766.webp"}]
     * error_code : 0
     */
    private String reason;
    private int error_code;
    private List<DataBean> data;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * name : 大赢家2020
         * tag : 大陆，2020
         * updateStatus : 评分：6.8
         * mvid : 19af39277b
         * video_type : movie
         * poster : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2591844862.webp
         */
        private String name;
        private String tag;
        private String updateStatus;
        private String mvid;
        private String video_type;
        private String poster;
        // 是否已经收藏了
        private boolean collect = false;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getUpdateStatus() {
            return updateStatus;
        }

        public void setUpdateStatus(String updateStatus) {
            this.updateStatus = updateStatus;
        }

        public String getMvid() {
            return mvid;
        }

        public void setMvid(String mvid) {
            this.mvid = mvid;
        }

        public String getVideo_type() {
            return video_type;
        }

        public void setVideo_type(String video_type) {
            this.video_type = video_type;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

    }
}
