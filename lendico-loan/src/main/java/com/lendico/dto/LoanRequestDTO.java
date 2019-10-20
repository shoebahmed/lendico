package com.lendico.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LoanRequestDTO {
  
  @ApiModelProperty(value = "Total loan amount", example="5000")
  @NotNull
  private Double loanAmount;
  
  @ApiModelProperty(value = "Nominal interest rate", example="5")
  @NotNull
  private Double nominalRate;
  
  @ApiModelProperty(value = "Duration in months", example="24")
  @NotNull
  private Integer duration;
  
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @ApiModelProperty(value = "Date of disbursement", example="2019-01-01T00:00:01Z")
  @NotNull
  private LocalDateTime startDate;
}
