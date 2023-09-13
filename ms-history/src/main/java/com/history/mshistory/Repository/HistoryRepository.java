package com.history.mshistory.Repository;

import com.history.mshistory.Entity.History;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoryRepository extends MongoRepository<History, String> {
}
