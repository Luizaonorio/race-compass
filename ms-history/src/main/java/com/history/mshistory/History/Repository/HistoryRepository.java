package com.history.mshistory.History.Repository;

import com.history.mshistory.History.Entity.History;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoryRepository extends MongoRepository<History, String> {
}
