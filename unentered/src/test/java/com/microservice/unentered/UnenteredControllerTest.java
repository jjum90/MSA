package com.microservice.unentered;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = true)
public class UnenteredControllerTest {

   private MockMvc mockMvc;

   @Autowired
   private WebApplicationContext context;

   @Autowired
   private ObjectMapper objectMapper;

   @MockBean
   private UnenteredService unenteredService;

   @BeforeEach
   public void setUp() {
       mockMvc = MockMvcBuilders.webAppContextSetup(context)
               .addFilters(new CharacterEncodingFilter("UTF-8"))
               .alwaysDo(MockMvcResultHandlers.print())
               .build();
   }

   @Test
   public void isUnenteredTest() throws Exception {
       mockMvc.perform(MockMvcRequestBuilders
               .get("/api/v1/unentered/{memberId}", 1)
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status().isOk());
   }

}
