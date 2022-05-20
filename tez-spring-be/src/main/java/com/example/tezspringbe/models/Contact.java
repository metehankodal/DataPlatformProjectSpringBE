package com.example.tezspringbe.models;

import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author meteh
 * @create 2.05.2022 18:41
 *//*
 * @created 05
 * @author meteh
 */

@Data
@Document
public class Contact {
    @Id
    private String id;
    private String nameSurname;
    private String email;
    private String content;

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Contact(String nameSurname, String email, String content) {
        this.nameSurname = nameSurname;
        this.email = email;
        this.content = content;
    }
}

