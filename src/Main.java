import in.cerjofun.dto.Movie;
import in.cerjofun.dto.Review;
import in.cerjofun.dto.User;
import in.cerjofun.service.MovieService;
import in.cerjofun.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan on 19/02/21
 */
public class Main {

    public static void main(String[] args) {
        UserService userService = new UserService();
        MovieService movieService=new MovieService(userService);


        List<String> genres = new ArrayList<>();
        genres.add("Action");
        genres.add("Comedy");
        Movie movie=new Movie("Don","2006", genres);
        movieService.addMovie(movie);

        genres = new ArrayList<>();
        genres.add("Drama");
        movie=new Movie("Tiger","2008", genres);
        movieService.addMovie(movie);

        genres = new ArrayList<>();
        genres.add("Comedy");
        movie=new Movie("Padmaavat","2006", genres);
        movieService.addMovie(movie);


        genres = new ArrayList<>();
        genres.add("Drama");
        movie=new Movie("Lunchbox","2021", genres);
        movieService.addMovie(movie);

        genres = new ArrayList<>();
        genres.add("Drama");
        movie=new Movie("Guru","2006", genres);
        movieService.addMovie(movie);


        genres = new ArrayList<>();
        genres.add("Romance");
        genres.add("Drama");
        movie=new Movie("Metro","2006", genres);
        movieService.addMovie(movie);


        userService.addUser(new User("SRK"));
        userService.addUser(new User("Salman"));
        userService.addUser(new User("Deepika"));
        userService.addUser(new User("Mohan"));
        userService.addUser(new User("Krishna"));





        Review review=new Review("SRK","Don",2);
        movieService.addReview(review);

        review=new Review("SRK","Metro",2);
        movieService.addReview(review);

        review=new Review("SRK","Lunchbox",2);
        movieService.addReview(review);

        review=new Review("Deepika","Tiger",2);
        movieService.addReview(review);

        review=new Review("Deepika","Lunchbox",5);
        movieService.addReview(review);

        review=new Review("Deepika","Metro",2);
        movieService.addReview(review);

        review=new Review("Salman","Tiger",5);
        movieService.addReview(review);

        review=new Review("Mohan","Tiger",5);
        movieService.addReview(review);

        review=new Review("Mohan","Metro",50);
        movieService.addReview(review);


        review=new Review("Mohan","Lunchbox",2);
        movieService.addReview(review);

        movieService.printMoviesAndReviews();
        userService.print();

        movieService.printTopReview(2,"Drama");

        movieService.printAverageReviewScoreForYear("2006");

        movieService.printAverageReviewScoreForMovie("Metro");
    }
}
