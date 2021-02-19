package in.cerjofun.service;

import in.cerjofun.dto.Movie;
import in.cerjofun.dto.Review;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mohan on 19/02/21
 */
public class MovieService extends BaseService {

    private static final Map<String, Movie> movies;
    private static final Map<String, List<Review>> reviewsMap;
    private final UserService userService;

    static {
        movies = new HashMap<>();
        reviewsMap = new HashMap<>();
    }

    public MovieService(UserService userService) {
        this.userService = userService;
    }


    public void addMovie(Movie movie) {
        if (checkMovieExists(movie.getName())) {
            throwException("Already movie exists with the name");
        }
        movies.put(movie.getName(), movie);
    }

    public Movie getMovie(String movieName) {
        if (!checkMovieExists(movieName)) {
            throwException("Movie does not exists with the name");
        }
        return movies.get(movieName);
    }

    private boolean checkMovieExists(String movieName) {
        return Objects.nonNull(movies.get(movieName));
    }

    public void addReview(Review review) {
        if (!checkMovieExists(review.getMovieName())) {
            throwException("Movie yet to be released");
        }

        if (!userService.checkUserExists(review.getUserName())) {
            throwException("Uer does not exists");
        }
        List<Review> reviews = reviewsMap.get(review.getUserName());
        if (Objects.nonNull(reviews)) {
            boolean isReviewedByUser = reviews
                    .stream().map(Review::getMovieName)
                    .anyMatch(x -> x.equalsIgnoreCase(review.getMovieName()));
            if (isReviewedByUser) {
                throwException("multiple reviews not allowed");
            }
        } else {
            reviews = new ArrayList<>();
        }
        reviews.add(review);

        if (reviews.size() > 2) {
            userService.markAsCriticUser(review.getUserName());
        }

        reviewsMap.put(review.getUserName(), reviews);
    }

    public void printTopReview(int n, String genre) {
        Map<String, Integer> movieNameVsTotalReviewMap = new HashMap<>();
        reviewsMap.forEach((userName, reviews) -> {
            if (userService.getUser(userName).isCritic()) {
                reviews.forEach(review -> {
                    String movieName = review.getMovieName();
                    Movie movie = getMovie(movieName);
                    if (movie.getGenres().contains(genre)) {
                        Integer totalReviewScore = movieNameVsTotalReviewMap.get(movieName);
                        if (Objects.isNull(totalReviewScore)) {
                            totalReviewScore = 0;
                        }
                        totalReviewScore += review.getReviewScore();
                        movieNameVsTotalReviewMap.put(movieName, totalReviewScore);
                    }
                });
            }
        });
        SortedMap<Integer, String> topReviewMap = new TreeMap<>(Collections.reverseOrder());
        movieNameVsTotalReviewMap.forEach((movie, review) -> topReviewMap.put(review, movie));
        List<String> movieNames = new ArrayList<>(topReviewMap.values());
        System.out.println("\n\nTop " + n + " critic review for " + genre + " as follows ");

        List<String> finalMovieNames = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            finalMovieNames.add(movieNames.get(i));
        }
        System.out.println(String.join(",",finalMovieNames));
    }


    public void printMoviesAndReviews() {
        System.out.println("Movies : " + movies);
        System.out.println("Reviews : " + reviewsMap);
    }

    public void printAverageReviewScoreForYear(String releaseYear) {
        AtomicInteger sumReviewScores = new AtomicInteger();
        AtomicInteger count = new AtomicInteger();
        reviewsMap.values().forEach(reviews -> reviews.forEach(review -> {
            if (getMovie(review.getMovieName()).getReleasedYear().equals(releaseYear)) {
                sumReviewScores.addAndGet(review.getReviewScore());
                count.getAndIncrement();
            }
        }));

        System.out.println("\n\nAverageReviewScoreForYear : " + releaseYear + " is  : " + (sumReviewScores.get() / count.get()));
    }

    public void printAverageReviewScoreForMovie(String movieName) {
        AtomicInteger sumReviewScores = new AtomicInteger();
        AtomicInteger count = new AtomicInteger();
        reviewsMap.values().forEach(reviews -> reviews.forEach(review -> {
            if ((review.getMovieName().equals(movieName))) {
                sumReviewScores.addAndGet(review.getReviewScore());
                count.getAndIncrement();
            }
        }));

        System.out.println("\n\nAverageReviewScoreForMovie : " + movieName + " is  : " + (sumReviewScores.get() / count.get()));
    }
}