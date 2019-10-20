package com.lendico.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.lendico.dto.LoanRequestDTO;
import com.lendico.service.AmortizationGenerator;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PlanGeneratorControllerImpl implements PlanGeneratorController {
  
  private final AmortizationGenerator amortizationGenerator;
  
  @Override
  public ResponseEntity<?> buildAmortizationSchedule(LoanRequestDTO loanrequestDTO) {
    
    return new ResponseEntity<>(amortizationGenerator.buildAmortizationSchedule(loanrequestDTO.getStartDate(), loanrequestDTO.getLoanAmount(), 
        loanrequestDTO.getNominalRate(), loanrequestDTO.getDuration()), HttpStatus.OK);
  }
}
