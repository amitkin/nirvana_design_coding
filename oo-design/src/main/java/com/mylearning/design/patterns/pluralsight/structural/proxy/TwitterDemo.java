package com.mylearning.design.patterns.pluralsight.structural.proxy;

public class TwitterDemo {

    public static void main(String[] args) {
        TwitterService service = (TwitterService) SecurityProxy.newInstance(new TwitterServiceImpl());

        System.out.println("service.getTimeline: " + service.getTimeline("gregdferrell"));

        try {
            service.postToTimeline("gregdferrell", "message to post");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
