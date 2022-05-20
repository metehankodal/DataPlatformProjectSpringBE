package com.example.tezspringbe.repos;

import com.example.tezspringbe.models.Dataset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @Author meteh
 * @create 6.05.2022 20:10
 */
public interface DatasetRepo extends MongoRepository<Dataset,String> {
    @Query("{onaylandiMi:onaylanmadi}")
    List<Dataset> getAllOnaylanmadi(String onaylandimi);
}