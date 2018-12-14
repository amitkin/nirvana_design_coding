package com.mylearning.services;

import org.springframework.stereotype.Component;

@Component
public interface TimeService {

    public long getCurrentTimestamp();

    public long getLatestValidTransactionTimestamp();

    public boolean isValidTransactionTimestamp(final long timestamp);

    public boolean areSameBucketTimestamps(long timestamp1, long timestamp2);

}
