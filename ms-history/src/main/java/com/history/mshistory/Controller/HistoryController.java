package com.history.mshistory.Controller;

import com.history.mshistory.DTO.HistoryDTOResponse;
import com.history.mshistory.Service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/histories")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<HistoryDTOResponse> findHistoryById(@PathVariable String id) {
        return new ResponseEntity<>(historyService.findHistoryById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<HistoryDTOResponse>> findAllHistories() {
        return new ResponseEntity<>(historyService.findAllHistories(), HttpStatus.OK);
    }
}
