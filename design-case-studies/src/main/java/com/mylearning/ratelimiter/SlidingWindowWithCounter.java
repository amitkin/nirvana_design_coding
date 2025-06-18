package com.mylearning.ratelimiter;

import redis.clients.jedis.Jedis;
import java.time.Instant;

public class SlidingWindowWithCounter {
    private final Jedis jedis;
    private final String keyPrefix;
    private final int windowSizeInSeconds;
    private final int maxRequests;

    public SlidingWindowWithCounter(Jedis jedis, String keyPrefix, int windowSizeInSeconds, int maxRequests) {
        this.jedis = jedis;
        this.keyPrefix = keyPrefix;
        this.windowSizeInSeconds = windowSizeInSeconds;
        this.maxRequests = maxRequests;
    }

    public boolean allowRequest() {
        long now = Instant.now().getEpochSecond();
        String currentBucket = keyPrefix + ":" + now;

        // Increment the counter for the current bucket
        long count = jedis.incr(currentBucket);

        // Set expiration for the bucket to avoid memory leaks
        jedis.expire(currentBucket, windowSizeInSeconds);

        // Calculate total requests in the last time window
        long totalCount = 0;
        for (long i = now - windowSizeInSeconds + 1; i <= now; i++) {
            String bucket = keyPrefix + ":" + i;
            String value = jedis.get(bucket);
            if (value != null) {
                totalCount += Long.parseLong(value);
            }
        }

        return totalCount <= maxRequests;
    }

    //Requests per minute exceeded, deny the request
    public static void main(String[] args) {
        try (Jedis jedis = new Jedis("localhost", 6379)) {
            SlidingWindowWithCounter limiter = new SlidingWindowWithCounter(jedis, "rate:user:123", 60, 5);

            for (int i = 1; i <= 7; i++) {
                boolean allowed = limiter.allowRequest();
                System.out.println("Request " + i + " allowed: " + allowed);
                Thread.sleep(1000); // Simulating 1-second interval
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
