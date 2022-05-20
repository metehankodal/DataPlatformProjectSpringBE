package com.example.tezspringbe.repos;

import com.example.tezspringbe.models.Dataset;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author meteh
 * @create 6.05.2022 20:10
 */
public interface DatasetRepo extends MongoRepository<Dataset,String> {
}