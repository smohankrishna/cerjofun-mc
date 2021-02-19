package in.cerjofun.dto;

import java.util.List;

/**
 * Created by mohan on 19/02/21
 */
public class Movie {

    private String name;
    private String releasedYear;
    private List<String> genres;

    public Movie(String name, String releasedYear, List<String> genres) {
        this.name = name;
        this.releasedYear = releasedYear;
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(String releasedYear) {
        this.releasedYear = releasedYear;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", releasedYear='" + releasedYear + '\'' +
                ", genres=" + genres +
                '}';
    }
}
