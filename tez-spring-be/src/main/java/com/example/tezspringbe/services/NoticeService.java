package com.example.tezspringbe.services;


import com.example.tezspringbe.models.Contact;
import com.example.tezspringbe.models.Notice;
import com.example.tezspringbe.repos.ContactRepo;
import com.example.tezspringbe.repos.NoticeRepo;
import com.example.tezspringbe.models.AnalysisRequest;
import com.example.tezspringbe.repos.AnalysisRequestRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Service
@AllArgsConstructor
public class NoticeService {

    private  NoticeRepo noticeRepo;
    private ContactRepo contactRepo;
    private AnalysisRequestRepo analysisRequestRepo;

    public List<Notice> getAllNotice() {
        List<Notice> notices = noticeRepo.findAll();
        Collections.sort(notices,((o1, o2) -> o2.getDateOfPublish().compareTo(o1.getDateOfPublish())));
        return  notices;
    }

    public boolean createNewContact(Contact contact){
        Contact contactResponse = contactRepo.insert(contact);
        if(contactResponse != null) {
            return true;
        }
        else {
            return false;
        }

    }

    public boolean createNewAnalysisRequest(AnalysisRequest analysisRequest){
        AnalysisRequest analysisRequestResponse = analysisRequestRepo.insert(analysisRequest);
        if(analysisRequestResponse != null) {
            return true;
        }
        else {
            return false;
        }

    }
}

