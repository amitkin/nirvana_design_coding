package com.mylearning.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mylearning.exceptions.FieldNotParseableException;
import com.mylearning.services.TimeService;
import com.mylearning.util.TimeConvertor;

public class Statistic {

    private final TimeService timeService;

    @JsonProperty("sum")
    private String sum = BigDecimal.ZERO.toString();

    @JsonProperty("avg")
    private String avg = BigDecimal.ZERO.toString();

    @JsonProperty("max")
    private String max = Long.toString(Long.MIN_VALUE);

    @JsonProperty("min")
    private String min = Long.toString(Long.MAX_VALUE);

    @JsonProperty("count")
    private long count;

    @JsonIgnore
    private long timestamp;

    public Statistic(TimeService timeService) {
        this.timeService = timeService;
    }

    public Statistic(final TimeService timeService, final Transaction transaction, final Statistic statisticTillNow) throws
            FieldNotParseableException {
        this.timeService = timeService;
        this.timestamp = Objects.requireNonNull(TimeConvertor.fromISO8601UTC(transaction.getTimestamp())).getTime();

        BigDecimal amount = new BigDecimal(transaction.getAmount());
        this.sum = amount.toString();
        this.count = 1;
        this.avg = amount.toString();
        this.max = amount.toString();
        this.min = amount.toString();
        mergeStatistics(statisticTillNow);
    }

    public void mergeStatistics(final Statistic statisticTillNow) {
        if (canBeMerged(statisticTillNow)) {
            BigDecimal b1 = new BigDecimal(statisticTillNow.avg).multiply(new BigDecimal(statisticTillNow.count));
            BigDecimal b2 = new BigDecimal(this.avg).multiply(new BigDecimal(this.count));
            BigDecimal b3 = b1.add(b2);

            this.avg = b3.divide(new BigDecimal(statisticTillNow.count + this.count), 2, RoundingMode.HALF_UP).toString();
            this.sum = new BigDecimal(statisticTillNow.sum).add(new BigDecimal(this.sum)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            this.count = statisticTillNow.count + this.count;
            this.max = new BigDecimal(this.max).max(new BigDecimal(statisticTillNow.max)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            this.min = new BigDecimal(this.min).min(new BigDecimal(statisticTillNow.min)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        }
    }

    private boolean canBeMerged(final Statistic statisticTillNow) {
        return statisticTillNow != null &&
                statisticTillNow.hasData() &&
                timeService.isValidTransactionTimestamp(statisticTillNow.getTimestamp()) &&
                (!this.hasData() || this.getTimestamp() == 0 || timeService
                        .areSameBucketTimestamps(this.timestamp, statisticTillNow.getTimestamp()));
    }

    public void reset() {
        this.sum = BigDecimal.ZERO.toString();
        this.avg = BigDecimal.ZERO.toString();
        this.max = Long.toString(Long.MIN_VALUE);
        this.min = Long.toString(Long.MAX_VALUE);
        this.count = 0;
        this.timestamp = 0;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean hasData() {
        return count > 0;
    }

    @Override
    public String toString() {
        return String.format("Transaction[sum=%s, avg=%s, max=%s, min=%s, count=%s]", sum, avg, max, min, count);
    }

}
