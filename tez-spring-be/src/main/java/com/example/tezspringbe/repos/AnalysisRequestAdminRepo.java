package com.example.tezspringbe.repos;

import com.example.tezspringbe.models.DatasetAnalysis;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author meteh
 * @create 28.05.2022 22:45
 */
public interface AnalysisRequestAdminRepo extends MongoRepository<DatasetAnalysis,String> {

}
