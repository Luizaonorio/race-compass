package com.history.mshistory.Service.Exceptions;

public class HistoryNotFoundException extends RuntimeException {
    public HistoryNotFoundException(String id) {
        super("Race with id " + id + " not found");
    }
}