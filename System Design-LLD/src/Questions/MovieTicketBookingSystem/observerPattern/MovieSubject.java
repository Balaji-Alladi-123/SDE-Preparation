package Questions.MovieTicketBookingSystem.observerPattern;

import Questions.MovieTicketBookingSystem.models.Movie;

import java.util.ArrayList;
import java.util.List;

public abstract  class MovieSubject {
    private List<MovieObserver> observerList = new ArrayList<>();

    public void addObservers(MovieObserver observer) {
        observerList.add(observer);
    }

    public  void removeObservers(MovieObserver observer) {
      observerList.remove(observer);
    }

    public void notifyObserver() {
        for(MovieObserver observer: observerList) {
            observer.update((Movie) this);
        }
    }
}
