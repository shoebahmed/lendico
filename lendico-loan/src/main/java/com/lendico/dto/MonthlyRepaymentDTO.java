package com.lendico.dto;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.lendico.serializer.JsonDoubleSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MonthlyRepaymentDTO {
  
  @ApiModelProperty(value = "Monthly Payment amount")
  @JsonSerialize(using = JsonDoubleSerializer.class)
  private Double borrowerPaymentAmount;
  
  @ApiModelProperty(value = "Date for monthly Payment amount")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime date;
  
  @ApiModelProperty(value = "Outstanding Loan Amount")
  @JsonSerialize(using = JsonDoubleSerializer.class)
  private Double initialOutstandingPrincipal;
  
  @ApiModelProperty(value = "Monthly interest amount")
  @JsonSerialize(using = JsonDoubleSerializer.class)
  private Double interest;
  
  @ApiModelProperty(value = "Monthly Principal amount")
  @JsonSerialize(using = JsonDoubleSerializer.class)
  private Double principal;
  
  @ApiModelProperty(value = "Balance loan amount")
  @JsonSerialize(using = JsonDoubleSerializer.class)
  private Double remainingOutstandingPrincipal;
}
