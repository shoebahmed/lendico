package com.lendico.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import java.time.LocalDateTime;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lendico.controller.PlanGeneratorControllerImpl;
import com.lendico.dto.LoanRequestDTO;
import com.lendico.dto.MonthlyRepaymentDTO;
import com.lendico.service.AmortizationGenerator;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PlanGeneratorControllerTest{
  
  private static final String APIURL = "/api/generate-plan";
  
  @InjectMocks
  private PlanGeneratorControllerImpl planGeneratorController;
  
  @Mock
  private AmortizationGenerator amortizationGenerator;

  private MockMvc mvc;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setup() {
    initializeMVC();
    this.objectMapper = new ObjectMapper();
  }

  private void initializeMVC() {
    mvc = standaloneSetup(planGeneratorController).build();
  }

  @Test
  void buildAmortizationSchedule_MonthlyRepaymentList_Ok() throws Exception {
    
    LoanRequestDTO loanRequestDTO = new LoanRequestDTO(5000D, 5D, 24, LocalDateTime.now());
    MonthlyRepaymentDTO monthlyRepaymentDTO = new MonthlyRepaymentDTO(219.36, LocalDateTime.now(),5000D, 20.83, 4801.48, 4602.13);
    
    Mockito.when(amortizationGenerator.buildAmortizationSchedule(any(LocalDateTime.class), any(Double.class), any(Double.class), any(Integer.class)))
        .thenReturn(Collections.singletonList(monthlyRepaymentDTO));
    

    MockHttpServletResponse response = mvc.perform(post(APIURL).accept(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(loanRequestDTO))
        .contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).contains(monthlyRepaymentDTO.getPrincipal().toString());
    assertThat(response.getContentAsString()).contains(monthlyRepaymentDTO.getInterest().toString());
  }
}
