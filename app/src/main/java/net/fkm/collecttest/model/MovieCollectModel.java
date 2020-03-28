package net.fkm.collecttest.model;

public class MovieCollectModel extends BaseModel {

    private String movieId;

    private String movieName;

    private String poster;

    private String movieTag;

    private String videoType;

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

    public String getMovieTag() {
        return movieTag;
    }

    public void setMovieTag(String movieTag) {
        this.movieTag = movieTag;
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
