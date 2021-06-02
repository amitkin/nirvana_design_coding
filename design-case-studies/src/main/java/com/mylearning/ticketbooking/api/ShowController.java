package com.mylearning.ticketbooking.api;

import com.mylearning.ticketbooking.model.Movie;
import com.mylearning.ticketbooking.model.Screen;
import com.mylearning.ticketbooking.model.Seat;
import com.mylearning.ticketbooking.model.Show;
import com.mylearning.ticketbooking.services.MovieService;
import com.mylearning.ticketbooking.services.SeatAvailabilityService;
import com.mylearning.ticketbooking.services.ShowService;
import com.mylearning.ticketbooking.services.TheatreService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ShowController {
    private final SeatAvailabilityService seatAvailabilityService;
    private final ShowService showService;
    private final TheatreService theatreService;
    private final MovieService movieService;

    public String createShow(@NonNull final String movieId, @NonNull final String screenId, @NonNull final Date startTime,
                             @NonNull final Integer durationInSeconds) {
        final Screen screen = theatreService.getScreen(screenId);
        final Movie movie = movieService.getMovie(movieId);
        return showService.createShow(movie, screen, startTime, durationInSeconds).getId();
    }

    public List<String> getAvailableSeats(@NonNull final String showId) {
        final Show show = showService.getShow(showId);
        final List<Seat> availableSeats = seatAvailabilityService.getAvailableSeats(show);
        return availableSeats.stream().map(Seat::getId).collect(Collectors.toList());
    }
}
