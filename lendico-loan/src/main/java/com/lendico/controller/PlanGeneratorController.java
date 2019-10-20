package com.lendico.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lendico.dto.LoanRequestDTO;
import com.lendico.dto.MonthlyRepaymentDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = {"/api/generate-plan", "/api/v1/generate-plan"})
@Api(value = "loginapi", description = "API Operations for Loan Repayment Plan.", tags = {"Repayment Plan"})
public interface PlanGeneratorController {

  
  @PostMapping
  @ApiOperation(value = "Plan generator API operation",
      response = MonthlyRepaymentDTO.class)
  public ResponseEntity<?> buildAmortizationSchedule(
      @ApiParam(name = "LoanRequestDTO", value = "Laon Request") 
      @RequestBody LoanRequestDTO loanrequestDTO);
}
