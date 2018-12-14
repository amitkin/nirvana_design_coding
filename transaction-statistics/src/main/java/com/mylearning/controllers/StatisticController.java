package com.mylearning.controllers;

import com.mylearning.services.TransactionService;
import com.mylearning.models.Statistic;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticController {

  private static final Logger LOGGER = LoggerFactory.getLogger(StatisticController.class);
  private final TransactionService transactionService;

  @Autowired
  public StatisticController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }


  @ApiOperation(value = "Last 60s statistics", notes = "Returns last 60s addTransaction statistic", response = Statistic.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = Statistic.class)})
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity statistics() {
    Statistic statistic = transactionService.getStatistics();
    LOGGER.info("Response : {}", statistic);
    return ResponseEntity.status(HttpStatus.OK).body(statistic);
  }

}
