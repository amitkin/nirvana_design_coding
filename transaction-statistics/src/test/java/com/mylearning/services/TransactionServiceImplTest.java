package com.mylearning.services;

import org.junit.Test;

public class TransactionServiceImplTest {

    @Test
    public void testSaveTransaction() {
        /*long time = System.currentTimeMillis();
        Date dateTime = new Date(time);
        String dateTimeString = TimeConvertor.toISO8601UTC(dateTime);
        TimeService timeService = Mockito.mock(TimeService.class);
        Mockito.when(timeService.getCurrentUTCEpochMilliseconds()).then(invocationOnMock -> time);
        Mockito.when(
                timeService.validateTransaction(ArgumentMatchers.any(Long.class), ArgumentMatchers.any(Long.class)))
                .then(invocationOnMock -> true);

        TransactionService transactionService = new TransactionServiceImpl(timeService, 60000, 1000);
        Transaction addTransaction = new Transaction("12", dateTimeString);
        Assert.assertTrue(transactionService.addTransaction(addTransaction));
        addTransaction = new Transaction("12", dateTimeString);
        Mockito.when(
                timeService.validateTransaction(ArgumentMatchers.any(Long.class), ArgumentMatchers.any(Long.class)))
                .then(invocationOnMock -> false);
        Assert.assertFalse(transactionService.addTransaction(addTransaction));*/
    }

    @Test
    public void testGetStatistics() {
        /*long time = System.currentTimeMillis();
        Date dateTime = new Date(time);
        String dateTimeString = TimeConvertor.toISO8601UTC(dateTime);
        TimeService timeService = Mockito.mock(TimeService.class);
        Mockito.when(timeService.getCurrentUTCEpochMilliseconds()).then(invocationOnMock -> time);
        Mockito.when(
                timeService.validateTransaction(ArgumentMatchers.any(Long.class), ArgumentMatchers.any(Long.class)))
                .then(invocationOnMock -> true);

        TransactionService transactionService = new TransactionServiceImpl(timeService, 60000, 1000);
        Transaction addTransaction = new Transaction("12", dateTimeString);
        Assert.assertTrue(transactionService.addTransaction(addTransaction));
        addTransaction = new Transaction("4", dateTimeString);
        Assert.assertTrue(transactionService.addTransaction(addTransaction));

        Statistic statistic = transactionService.getStatistics();
        Assert.assertEquals(2, statistic.getCount());
        Assert.assertEquals(8, new Double(statistic.getAvg()), 0.001);
        Assert.assertEquals(12, new Double(statistic.getMax()), 0.001);
        Assert.assertEquals(4, new Double(statistic.getMin()), 0.001);
        Assert.assertEquals(16, new Double(statistic.getSum()), 0.001);*/
    }

}
