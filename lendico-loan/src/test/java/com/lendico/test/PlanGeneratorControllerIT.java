package com.lendico.test;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lendico.LendicoLoanApplication;
import com.lendico.dto.LoanRequestDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LendicoLoanApplication.class)
@AutoConfigureMockMvc
public class PlanGeneratorControllerIT {

  private static final String APIURL = "/api/generate-plan";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void setup() {

  }

  @Test
  void createProduct_Product_Ok() throws Exception {

    LoanRequestDTO loanRequestDTO = new LoanRequestDTO(5000D, 5D, 24, LocalDateTime.now());

    mockMvc.perform(post(APIURL)
           .contentType(MediaType.APPLICATION_JSON)
           .content(this.objectMapper.writeValueAsString(loanRequestDTO)))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.*", hasSize(24)));
  }
}
