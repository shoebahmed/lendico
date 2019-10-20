package com.lendico.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.lendico.dto.MonthlyRepaymentDTO;

@Component("amortizationGenerator")
public class AmortizationGenerator {

  public List<MonthlyRepaymentDTO> buildAmortizationSchedule(LocalDateTime startDate, double principal, double annualInterestRate,
      int numMonths) {
    
    double interestPaid, principalPaid, newBalance;
    double monthlyInterestRate, monthlyPayment;
    int month;
    
    List<MonthlyRepaymentDTO> monthlyRepaymentList = new ArrayList<>();
    
    // Output monthly payment and total payment
    monthlyInterestRate = annualInterestRate / 12;
    monthlyPayment = monthlyPayment(principal, monthlyInterestRate, numMonths);
    
    for (month = 1; month <= numMonths; month++) {
      // Compute amount paid and new balance for each payment period
      interestPaid = principal * (monthlyInterestRate / 100);
      principalPaid = monthlyPayment - interestPaid;
      newBalance = principal - principalPaid;

      // Output the data item
      MonthlyRepaymentDTO monthlyRepaymentDTO = getScheduleItem(startDate, monthlyPayment, month, interestPaid, principalPaid, principal, newBalance);
      monthlyRepaymentList.add(monthlyRepaymentDTO);
      
      // Update the balance
      principal = newBalance;
    }
    
    return monthlyRepaymentList;
  }

  private double monthlyPayment(double loanAmount, double monthlyInterestRate, int numberOfMonths) {
    monthlyInterestRate /= 100;
    return loanAmount * monthlyInterestRate
        / (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfMonths));
  }

  private MonthlyRepaymentDTO getScheduleItem(LocalDateTime startDate, double monthlyPayment, int month, double interestPaid, double principalPaid,
      double principal, double newBalance) {
    
    return new MonthlyRepaymentDTO(monthlyPayment, startDate.plusMonths(--month), principal, interestPaid, principalPaid, newBalance);
  }
}
