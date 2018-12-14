package com.mylearning.controllers;

import com.mylearning.exceptions.FieldNotParseableException;
import com.mylearning.services.TimeService;
import com.mylearning.services.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatisticControllerTest {

    @LocalServerPort
    private int port;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private TimeService timeService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testStatistics() throws FieldNotParseableException {
        /*long time = System.currentTimeMillis() * 1000;
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD'T'hh:mm:ss.sssZ");
        Date dateTime = new Date(time);
        Transaction addTransaction = new Transaction("12.3343", dateFormat.format(dateTime));
        Mockito.when(timeService.isValidTransactionTimestamp(ArgumentMatchers.anyLong())).thenReturn(true);
        Statistic statistic = new Statistic(timeService, addTransaction, new Statistic(timeService));
        Mockito.when(transactionService.getStatistics()).thenReturn(statistic);

        ResponseEntity<Statistic> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/statistics",
        Statistic.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());
        Statistic response = responseEntity.getBody();
        Assert.assertEquals(12.33, new Double(response.getSum()), 0.001);
        Assert.assertEquals(12.33, new Double(response.getAvg()), 0.001);
        Assert.assertEquals(12.33, new Double(response.getMin()), 0.001);
        Assert.assertEquals(12.33, new Double(response.getMax()), 0.001);
        Assert.assertEquals(1, response.getCount());*/
    }

}
