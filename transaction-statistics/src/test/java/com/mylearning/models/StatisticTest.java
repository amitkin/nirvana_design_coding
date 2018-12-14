package com.mylearning.models;

import org.junit.Test;

public class StatisticTest {

    @Test
    public void testAddTransaction() {
        /*long time = System.currentTimeMillis() * 1000;
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD'T'hh:mm:ss.sssZ");
        Date dateTime = new Date(time);
        Transaction addTransaction = new Transaction("12.3343", dateFormat.format(dateTime));
        Statistic statistic = new Statistic(addTransaction);
        Assert.assertEquals(12.33, new Double(statistic.getSum()), 0.001);
        Assert.assertEquals(12.33, new Double(statistic.getAvg()), 0.001);
        Assert.assertEquals(12.33, new Double(statistic.getMin()), 0.001);
        Assert.assertEquals(12.33, new Double(statistic.getMax()), 0.001);
        Assert.assertEquals(1, statistic.getCount());

        addTransaction = new Transaction("4.2215", dateFormat.format(dateTime));
        statistic.addTransaction(addTransaction);
        Assert.assertEquals(16.55, new Double(statistic.getSum()), 0.001);
        Assert.assertEquals(8.28, new Double(statistic.getAvg()), 0.001);
        Assert.assertEquals(4.22, new Double(statistic.getMin()), 0.001);
        Assert.assertEquals(12.33, new Double(statistic.getMax()), 0.001);
        Assert.assertEquals(2, statistic.getCount());*/

    }

    @Test
    public void testAddStatistic() {

        /*long time = System.currentTimeMillis() * 1000;
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD'T'hh:mm:ss.sssZ");
        Date dateTime = new Date(time);
        Transaction addTransaction = new Transaction("12.3343", dateFormat.format(dateTime));
        Statistic statistic = new Statistic(addTransaction);
        Assert.assertEquals(12.33, new Double(statistic.getSum()), 0.001);
        Assert.assertEquals(12.33, new Double(statistic.getAvg()), 0.001);
        Assert.assertEquals(12.33, new Double(statistic.getMin()), 0.001);
        Assert.assertEquals(12.33, new Double(statistic.getMax()), 0.001);
        Assert.assertEquals(1, statistic.getCount());

        addTransaction = new Transaction("4.2215", dateFormat.format(dateTime));
        statistic.addTransaction(addTransaction);
        Assert.assertEquals(16.55, new Double(statistic.getSum()), 0.001);
        Assert.assertEquals(8.28, new Double(statistic.getAvg()), 0.001);
        Assert.assertEquals(4.22, new Double(statistic.getMin()), 0.001);
        Assert.assertEquals(12.33, new Double(statistic.getMax()), 0.001);
        Assert.assertEquals(2, statistic.getCount());

        addTransaction = new Transaction("8.1664", dateFormat.format(dateTime));
        Statistic statistic2 = new Statistic(addTransaction);

        TimeService timeService = Mockito.mock(TimeService.class);
        Mockito.when(timeService.getCurrentUTCEpochMilliseconds()).then(invocationOnMock -> time);
        Mockito.when(
                timeService.validateTransaction(ArgumentMatchers.any(Long.class), ArgumentMatchers.any(Long.class)))
                .then(invocationOnMock -> true);
        statistic.addStatistic(statistic2, timeService, time);
        Assert.assertEquals(24.72, new Double(statistic.getSum()), 0.001);
        Assert.assertEquals(8.24, new Double(statistic.getAvg()), 0.001);
        Assert.assertEquals(4.22, new Double(statistic.getMin()), 0.001);
        Assert.assertEquals(12.33, new Double(statistic.getMax()), 0.001);
        Assert.assertEquals(3, statistic.getCount());*/
    }

}
