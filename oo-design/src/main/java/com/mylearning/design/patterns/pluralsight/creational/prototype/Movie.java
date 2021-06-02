package com.mylearning.design.patterns.pluralsight.creational.prototype;

public class Movie extends Item {
    private String runtime;
    private Producer producer = new Producer();

    @Override
    protected Movie clone() throws CloneNotSupportedException {
        Movie movie;

        try {
            movie = (Movie) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error();
        }

        movie.setProducer((Producer) getProducer().clone());

        return movie;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}
