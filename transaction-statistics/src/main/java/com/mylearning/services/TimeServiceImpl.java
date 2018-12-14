package com.mylearning.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TimeServiceImpl implements TimeService {

    private final int duration;
    private final int interval;

    @Autowired
    public TimeServiceImpl(@Value("${app.duration}") int duration, @Value("${app.interval}") int interval) {
        this.duration = duration;
        this.interval = interval;
    }

    @Override
    public long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    @Override
    public long getLatestValidTransactionTimestamp() {
        return getCurrentTimestamp() - this.duration + this.interval;
    }

    @Override
    public boolean isValidTransactionTimestamp(final long timestamp) {
        return timestamp >= getLatestValidTransactionTimestamp();
    }

    @Override
    public boolean areSameBucketTimestamps(final long timestamp1, final long timestamp2) {
        final long lump1 = timestamp1 / this.interval;
        final long lump2 = timestamp2 / this.interval;
        return lump1 == lump2;
    }

}
