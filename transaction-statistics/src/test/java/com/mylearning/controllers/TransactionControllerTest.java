package com.mylearning.controllers;

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
public class TransactionControllerTest {

    @LocalServerPort
    private int port;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testSaveTransactionSuccess() {
        /*long time = System.currentTimeMillis() * 1000;
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD'T'hh:mm:ss.sssZ");
        Date dateTime = new Date(time);
        Transaction addTransaction = new Transaction("12.3343", dateFormat.format(dateTime));
        Mockito.when(transactionService.addTransaction(ArgumentMatchers.any()))
                .thenReturn(true);

        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity("http://localhost:" + port + "/transactions", addTransaction, String.class);
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());*/
    }

    @Test
    public void testSaveTransactionFail() {
        /*long time = System.currentTimeMillis() * 1000;
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD'T'hh:mm:ss.sssZ");
        Date dateTime = new Date(time);
        Transaction addTransaction = new Transaction("12.3343", dateFormat.format(dateTime));
        Mockito.when(transactionService.addTransaction(ArgumentMatchers.any()))
                .thenReturn(false);

        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity("http://localhost:" + port + "/transactions", addTransaction, String.class);
        Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());*/
    }

}
