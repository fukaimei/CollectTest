package net.fkm.collecttest.db.model;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * 电影收藏数据库表名
 */
public class MovieCollectDBModel extends RealmObject {

    // 影视id，主键
    @Required
    @PrimaryKey
    @Index
    private String movieId;
    // 影视名称
    @Required
    private String movieName;
    // 影视海报、封面
    @Required
    private String poster;
    // 影视标签
    @Required
    private String movieLabel;
    // 影视类型
    @Required
    private String videoType;
    // 添加到数据的时间
    @Required
    private String createdTime;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getMovieLabel() {
        return movieLabel;
    }

    public void setMovieLabel(String movieLabel) {
        this.movieLabel = movieLabel;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

}
