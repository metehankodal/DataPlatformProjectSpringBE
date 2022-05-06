package com.example.tezspringbe.services;


import com.example.tezspringbe.models.Contact;
import com.example.tezspringbe.models.NewDataRequestToDb;
import com.example.tezspringbe.models.Notice;
import com.example.tezspringbe.repos.ContactRepo;
import com.example.tezspringbe.repos.NewDataRequestRepo;
import com.example.tezspringbe.repos.NoticeRepo;
import com.example.tezspringbe.models.AnalysisRequest;
import com.example.tezspringbe.repos.AnalysisRequestRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private NewDataRequestRepo newDataRequestRepo;
    private static String UPLOADED_FOLDER = "D:\\uploaded_data_sets"; //burası farklı olabilir sende.

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

    public boolean createNewDataRequest(MultipartFile newFile, String newInfo)  {

        if(newFile.isEmpty()) {
            return false;
        }
        try {
            byte[] bytes = newFile.getBytes();
            String pathOfFile = UPLOADED_FOLDER.concat("\\").concat(newFile.getOriginalFilename().toLowerCase());
            Path path = Paths.get(pathOfFile);

            String[] infos = newInfo.split(",");
            String senderName = null;
            String senderEmail = null;

            for(int i =0;i<infos.length;i++) {
                String[] temp = infos[i].split(":");
                if(i==2) {
                    break;
                }
                if(temp[0].contains("Email")) {
                    senderEmail = temp[1];
                    senderEmail = senderEmail.replace("{","");
                    senderEmail = senderEmail.replace("}","");
                    senderEmail = senderEmail.replace("\"","");
                }
                else if (temp[0].contains("Name")) {
                    senderName = temp[1];
                    senderName = senderName.replaceAll("\"","");
                }

            }
            System.out.println(senderName);
            System.out.println(senderEmail);
            NewDataRequestToDb newDataRequestToDb = new NewDataRequestToDb(senderName,senderEmail,pathOfFile,"onaylanmadi",newFile.getContentType());
            Files.write(path,bytes);
            newDataRequestRepo.insert(newDataRequestToDb);



        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Hata alırsan 29'a bak.");
        }
        return true;
    }
}

