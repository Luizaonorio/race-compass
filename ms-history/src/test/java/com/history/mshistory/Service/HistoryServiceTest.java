package com.history.mshistory.Service;

import com.history.mshistory.DTO.HistoryDTOResponse;
import com.history.mshistory.Entity.History;
import com.history.mshistory.Repository.HistoryRepository;
import com.history.mshistory.Service.Exceptions.HistoryNotFoundException;
import com.history.mshistory.Model.RaceConversion;
import com.history.mshistory.Utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HistoryServiceTest {

    @InjectMocks
    private HistoryService historyService;

    @Mock
    private HistoryRepository historyRepository;

    @Spy
    private ModelMapper mapper;

    private static final String HISTORY = "Payload/History.json";
    private static final String RACE_CONVERSION = "Payload/Race_conversion.json";
    private static final String ID_HISTORY = "6500bb9526ef9b24a0812c47";
    @Test
    void findHistoryById() throws IOException {
        History history = JsonUtils.getObjectFromFile(HISTORY, History.class);
        HistoryDTOResponse historyDTOResponse = JsonUtils.getObjectFromFile(HISTORY, HistoryDTOResponse.class);

        when(historyRepository.findById(anyString())).thenReturn(Optional.ofNullable(history));
        HistoryDTOResponse response = historyService.findHistoryById(anyString());

        assertNotNull(response);
        assertEquals(historyDTOResponse, response);
    }

    @Test
    void findHistoryById_ERROR_HistoryNotFoundException() {
        when(historyRepository.findById(anyString())).thenReturn(Optional.empty());
        assertThrows(HistoryNotFoundException.class, () -> historyService.findHistoryById(anyString()));
    }

    @Test
    void findAllHistories() throws IOException {
        History history = JsonUtils.getObjectFromFile(HISTORY, History.class);
        HistoryDTOResponse historyDTOResponse = JsonUtils.getObjectFromFile(HISTORY, HistoryDTOResponse.class);

        when(historyRepository.findAll()).thenReturn(List.of(history));
        List<HistoryDTOResponse> response = historyService.findAllHistories();

        assertEquals(historyDTOResponse, response.get(0));
    }

    @Test
    void saveHistoryResultOnDB() throws IOException {
        RaceConversion raceConversion = JsonUtils.getObjectFromFile(RACE_CONVERSION, RaceConversion.class);

        History historyNow = new History();
        historyNow.setDate(new Date());
        historyNow.setRace(raceConversion);

        when(historyRepository.save(any())).thenReturn(historyNow);

        historyService.saveRaceResultOnDB(raceConversion);

        verify(historyRepository, times(1)).save(any());
    }
}