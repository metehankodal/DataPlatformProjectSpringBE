package com.example.tezspringbe.models;

public class AnalysisRequest {
    private String dataset_id;
    private String nameSurname;
    private String email;
    private String request;


    public String getId() {
        return dataset_id;
    }

    public void setId(String dataset_id) {
        this.dataset_id = dataset_id;
    }

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

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public AnalysisRequest(String dataset_id, String nameSurname, String email, String request) {
        this.dataset_id = dataset_id;
        this.nameSurname = nameSurname;
        this.email = email;
        this.request = request;
    }
}

