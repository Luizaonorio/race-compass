package com.history.mshistory.History.Service;

import com.history.mshistory.History.DTO.HistoryDTOResponse;
import com.history.mshistory.History.Entity.History;
import com.history.mshistory.History.Repository.HistoryRepository;
import com.history.mshistory.History.Service.Exceptions.HistoryNotFoundException;
import com.history.mshistory.Model.RaceConversion;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;

    private final ModelMapper mapper;

    public HistoryDTOResponse findHistoryById(String id) {
        History historyEntity = historyRepository.findById(id).orElseThrow(() -> new HistoryNotFoundException(id));
        HistoryDTOResponse historyResponse = mapper.map(historyEntity, HistoryDTOResponse.class);
        return historyResponse;
    }

    public List<HistoryDTOResponse> findAllHistories() {
        List<History> historyListEntity = historyRepository.findAll();
        List<HistoryDTOResponse> historyListResponse = historyListEntity.stream().map(history -> mapper.map(history, HistoryDTOResponse.class)).toList();
        return historyListResponse;
    }

    public void saveRaceResultOnDB(RaceConversion raceResult) {
        History newHistory = new History();
        newHistory.setDate(new Date());
        newHistory.setRace(raceResult);

        historyRepository.save(newHistory);
    }
}
