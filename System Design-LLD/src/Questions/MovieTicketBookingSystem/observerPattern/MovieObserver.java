package Questions.MovieTicketBookingSystem.observerPattern;

import Questions.MovieTicketBookingSystem.models.Movie;

public interface MovieObserver {
   void update(Movie movie);
}
