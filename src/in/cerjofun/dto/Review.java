package in.cerjofun.dto;

/**
 * Created by mohan on 19/02/21
 */
public class Review {
    private String userName;
    private String movieName;
    private Integer reviewScore;

    public Review(String userName, String movieName, Integer reviewScore) {
        this.userName = userName;
        this.movieName = movieName;
        this.reviewScore = reviewScore;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Integer getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(Integer reviewScore) {
        this.reviewScore = reviewScore;
    }

    @Override
    public String toString() {
        return "Review{" +
                "userName='" + userName + '\'' +
                ", movieName='" + movieName + '\'' +
                ", rating=" + reviewScore +
                '}';
    }
}
