package com.mylearning.design.patterns.pluralsight.structural.bridge;

import java.util.List;

public abstract class Printer {

    public String print(Formatter formatter) {
        return formatter.format(getHeader(), getDetails());
    }

    protected abstract String getHeader();

    protected abstract List<Detail> getDetails();

}
