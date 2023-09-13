package com.history.mshistory.Controller;

import com.history.mshistory.DTO.HistoryDTOResponse;
import com.history.mshistory.Service.Exceptions.HistoryNotFoundException;
import com.history.mshistory.Service.HistoryService;
import com.history.mshistory.Utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static com.history.mshistory.Utils.JsonUtils.objectMapper;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HistoryController.class)
class HistoryControllerTest {

    @MockBean
    private HistoryService historyService;

    @Autowired
    private MockMvc mockMvc;

    private static final String HISTORY = "Payload/History.json";
    private static final String ID_HISTORY = "6500bb9526ef9b24a0812c47";

    @Test
    void findHistoryById() throws Exception {
        HistoryDTOResponse history = JsonUtils.getObjectFromFile(HISTORY, HistoryDTOResponse.class);
        history.setDate(new Date());

        when(historyService.findHistoryById(ID_HISTORY)).thenReturn(history);

        mockMvc.perform(get("/api/v1/histories/get/{id}", ID_HISTORY))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_HISTORY))
                .andExpect(content().json(objectMapper.writeValueAsString(history)));

        verify(historyService, times(1)).findHistoryById(ID_HISTORY);
    }

    @Test
    void findHistoryById_ERROR_HistoryNotFoundException() throws Exception {
        when(historyService.findHistoryById(ID_HISTORY)).thenThrow(new HistoryNotFoundException("Race with ID: " + ID_HISTORY + " not found"));

        mockMvc.perform(get("/api/v1/histories/get/{id}", ID_HISTORY))
                .andExpect(status().isNotFound());

        verify(historyService, times(1)).findHistoryById(ID_HISTORY);
    }

    @Test
    void findAllHistories() throws Exception {
        HistoryDTOResponse history = JsonUtils.getObjectFromFile(HISTORY, HistoryDTOResponse.class);
        history.setDate(new Date());

        when(historyService.findAllHistories()).thenReturn(List.of(history));

        mockMvc.perform(get("/api/v1/histories/get"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(history))));

        verify(historyService, times(1)).findAllHistories();
    }
}