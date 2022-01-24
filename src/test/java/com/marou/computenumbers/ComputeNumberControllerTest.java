package com.marou.computenumbers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.marou.computenumbers.model.OutputStats;
import com.marou.computenumbers.model.RawData;
import com.marou.computenumbers.validation.InputValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = ComputeNumbersApplication.class)
@AutoConfigureMockMvc
class ComputeNumberControllerTest {

  public static final String API_EVENT = "/event";
  public static final String PAYLOAD = "1607341341814,0.0442672968,1282509067";
  public static final String API_STATS = "/stats";

  @Autowired
  MockMvc mockMvc;
  @MockBean
  ComputeNumberService service;
  @MockBean
  BackPressureService backPressureService;
  @MockBean
  InputValidator validator;

  @Test
  public void shouldAcceptInputAndCallThePublisher() throws Exception {

    RawData rawData = new RawData(PAYLOAD);
    when(validator.isValid(PAYLOAD)).thenReturn(true);

    mockMvc.perform(post(API_EVENT)
        .contentType(MediaType.TEXT_PLAIN)
        .content(PAYLOAD))
        .andExpect(status().isAccepted());

    verify(backPressureService).publishNumberCollectionEvent(rawData);
  }

  @Test
  public void shouldFailValidationAndRespondWithBadRequest() throws Exception {

    mockMvc.perform(post(API_EVENT)
        .contentType(MediaType.TEXT_PLAIN)
        .content("1607341341814,"))
        .andExpect(status().isBadRequest());

    verify(validator).isValid("1607341341814,");

  }

  @Test
  public void shouldReturnStats() throws Exception {
    OutputStats expectedOutput = new OutputStats(PAYLOAD);

    when(service.getStatistics()).thenReturn(expectedOutput);

    String output = mockMvc.perform(get(API_STATS).accept(MediaType.TEXT_PLAIN))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    Assertions.assertEquals(expectedOutput.getPayload(), output);

  }

}
