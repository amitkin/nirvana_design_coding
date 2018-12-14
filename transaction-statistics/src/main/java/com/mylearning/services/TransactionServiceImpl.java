package com.mylearning.services;

import com.mylearning.exceptions.FieldNotParseableException;
import com.mylearning.exceptions.InvalidJsonException;
import com.mylearning.exceptions.OlderTransactionException;
import com.mylearning.models.Transaction;
import com.mylearning.models.Statistic;
import com.mylearning.util.TimeConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service
@Configuration
public class TransactionServiceImpl implements TransactionService {

    private final Statistic[] statistics;
    private final Statistic cachedStatistic;
    private final Object[] writeLocks;
    private final Object readLock = new Object();
    private final TimeService timeService;
    private final int duration;
    private final int interval;

    @Autowired
    public TransactionServiceImpl(TimeService timeService, @Value("${app.duration}") int duration,
            @Value("${app.interval}") int interval) {
        this.timeService = timeService;
        this.duration = duration;
        this.interval = interval;
        this.statistics = new Statistic[duration / interval];
        this.cachedStatistic = new Statistic(timeService);
        this.writeLocks = new Object[this.statistics.length];
        initializeLocks();
    }

    private void initializeLocks() {
        for (int i = 0; i < writeLocks.length; i++) {
            writeLocks[i] = new Object();
        }
    }

    @Override
    public boolean addTransaction(Transaction transaction) throws OlderTransactionException, InvalidJsonException,
            FieldNotParseableException {
        Date timestamp = TimeConvertor.fromISO8601UTC(transaction.getTimestamp());
        if (timestamp != null) {
            assertValidTransaction(timestamp.getTime());
            final int index = getStatisticsIndexForTimestamp(timestamp.getTime());
            synchronized (writeLocks[index]) {
                statistics[index] = new Statistic(timeService, transaction, statistics[index]);
                //System.out.println(">>>>> Statistics length = " + statistics.length + " Index = " + index + " Count = " + statistics[index].getCount());
            }
            return true;
        } else {
            return false;
        }

    }

    private int getStatisticsIndexForTimestamp(final long timestamp) {
        return (int) ((timestamp / interval) % statistics.length);
    }

    private void assertValidTransaction(final long timestamp) throws OlderTransactionException {
        if (!timeService.isValidTransactionTimestamp(timestamp)) {
            throw new OlderTransactionException("Transaction is older than specified duration");
        }
    }

    @Override
    public Statistic getStatistics() {
        final long currentTime = timeService.getCurrentTimestamp();
        if (cachedStatistic.getTimestamp() < currentTime) {
            synchronized (readLock) {
                if (cachedStatistic.getTimestamp() < currentTime) {
                    cachedStatistic.reset();
                    for (int i = 0; i < statistics.length; i++) {
                        cachedStatistic.mergeStatistics(statistics[i]);
                    }
                    cachedStatistic.setTimestamp(currentTime);
                }
            }
        }
        return cachedStatistic;
    }

    @Override
    public boolean deleteTransactions() {
        Arrays.fill(this.statistics, new Statistic(timeService));
        return true;
    }

}
