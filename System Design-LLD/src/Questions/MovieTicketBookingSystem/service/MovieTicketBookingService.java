package Questions.MovieTicketBookingSystem.service;

import Questions.MovieTicketBookingSystem.models.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MovieTicketBookingService {
    private static MovieTicketBookingService instance;
    private final Map<Integer, City> cities;
    private final Map<Integer, Cinema> cinemas;
    private final Map<String, Show> shows;
    private final Map<String, Movie> movies;
    private final Map<Integer, User> users;

    MovieTicketBookingService() {
        this.cities = new ConcurrentHashMap<>();
        this.cinemas = new ConcurrentHashMap<>();
        this.movies = new ConcurrentHashMap<>();
        this.shows = new ConcurrentHashMap<>();
        this.users = new ConcurrentHashMap<>();
    }

    public static MovieTicketBookingService getInstance() {
        if (instance == null) {
            synchronized (MovieTicketBookingService.class) {
                if (instance == null) {
                    instance = new MovieTicketBookingService();
                }
            }
        }
        return instance;
    }

    public City addCities(String name) {
        City city = new City(name);
        cities.put(city.getId(), city);
        return city;
    }

    public Movie addMovies(String id, String title, int duration) {
        Movie movie = new Movie(id, title, duration);
        movies.putIfAbsent(movie.getId(), movie);
        return movie;
    }

    public Cinema addCinema(int id, String name, List<Screen> screens, int cityId) {
        City city = cities.get(cityId);
        Cinema cinema = new Cinema(id, name, screens, city);
        cinemas.putIfAbsent(id, cinema);
        return cinema;
    }

    public Show addShows(Show show) {
        shows.putIfAbsent(show.getId(), show);
        return show;
    }

    public User addUser(User user) {
        users.putIfAbsent(user.getId(), user);
        return user;
    }
}
