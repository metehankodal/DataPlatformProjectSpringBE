package com.example.tezspringbe.repos;

import com.example.tezspringbe.models.AnalysisRequest;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AnalysisRequestRepo extends MongoRepository<AnalysisRequest,String> {
}
