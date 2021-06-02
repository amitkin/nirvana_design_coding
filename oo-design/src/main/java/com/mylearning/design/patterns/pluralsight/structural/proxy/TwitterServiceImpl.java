package com.mylearning.design.patterns.pluralsight.structural.proxy;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class TwitterServiceImpl implements TwitterService {

    private static final String TWITTER_CONSUMER_KEY = "TODO";
    private static final String TWITTER_SECRET_KEY = "TODO";
    private static final String TWITTER_ACCESS_TOKEN = "TODO";
    private static final String TWITTER_ACCESS_TOKEN_SECRET = "TODO";

    @Override
    public String getTimeline(String screenName) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
                .setOAuthConsumerSecret(TWITTER_SECRET_KEY)
                .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);

        TwitterFactory tf = new TwitterFactory(cb.build());

        Twitter twitter = tf.getInstance();

        StringBuilder sb = new StringBuilder();
        try {
            Query query = new Query("from:" + screenName);
            QueryResult result;

            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    sb.append("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);
        } catch (TwitterException e) {
            e.printStackTrace();
            System.out.println("Failed to search tweets: " + e.getMessage());
        }

        return sb.toString();
    }

    @Override
    public void postToTimeline(String screenName, String message) {
        System.out.println("Shouldn't be able to see this");
    }
}
