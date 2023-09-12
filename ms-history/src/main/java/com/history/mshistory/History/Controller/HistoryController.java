package com.history.mshistory.History.Controller;

import com.history.mshistory.History.DTO.HistoryDTOResponse;
import com.history.mshistory.History.Service.HistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/histories")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<HistoryDTOResponse> findById(@PathVariable String id) {
        return new ResponseEntity<>(historyService.findHistoryById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/get/races")
    public ResponseEntity<List<HistoryDTOResponse>> findAll() {
        return new ResponseEntity<>(historyService.findAllHistories(), HttpStatus.OK);
    }
}
