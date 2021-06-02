package com.mylearning.design.patterns.pluralsight.structural.bridge;

import java.util.List;

public class HtmlFormatter implements Formatter {

    @Override
    public String format(String header, List<Detail> details) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>");
        sb.append(header);
        sb.append("</h1>");

        sb.append("<ul>");
        for (Detail detail : details) {
            sb.append("<li>");
            sb.append(detail.getLabel());
            sb.append(":");
            sb.append(detail.getValue());
            sb.append("/<li>");
        }
        sb.append("</ul>");

        return sb.toString();
    }
}
