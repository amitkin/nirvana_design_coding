package com.mylearning.ticketbooking.api;

import com.mylearning.ticketbooking.model.Movie;
import com.mylearning.ticketbooking.services.MovieService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class MovieController {
    final private MovieService movieService;

    public String createMovie(@NonNull final String movieName) {
        return movieService.createMovie(movieName).getId();
    }

}
