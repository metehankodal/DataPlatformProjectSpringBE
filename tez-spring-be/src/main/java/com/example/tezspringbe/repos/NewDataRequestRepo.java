package com.example.tezspringbe.repos;

import com.example.tezspringbe.models.NewDataRequestToDb;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @Author meteh
 * @create 6.05.2022 20:10
 */
public interface NewDataRequestRepo  extends MongoRepository<NewDataRequestToDb,String> {

    @Query("{onaylandiMi:onaylanmadi}")
    List<NewDataRequestToDb> getAllOnaylanmadi(String onaylandimi);

}
