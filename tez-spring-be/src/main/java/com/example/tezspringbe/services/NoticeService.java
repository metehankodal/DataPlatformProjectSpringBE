package com.example.tezspringbe.services;


import com.example.tezspringbe.models.*;
import com.example.tezspringbe.repos.*;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;


@Service
@AllArgsConstructor
public class NoticeService {

    private  NoticeRepo noticeRepo;
    private ContactRepo contactRepo;
    private AnalysisRequestRepo analysisRequestRepo;
    private DatasetRepo datasetRepo;
    private AdminsRepo adminsRepo;
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
            String description = null;

            for(int i =0;i<infos.length;i++) {
                String[] temp = infos[i].split(":");
                if(i==3) {
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
                else if (temp[0].contains("description"))
                {
                    description = temp[1];
                    description = description.replace("{","");
                    description = description.replace("}","");
                    description = description.replace("\"","");
                }

            }
            System.out.println(senderName);
            System.out.println(senderEmail);
            Dataset newDataRequestToDb = new Dataset(senderName,senderEmail,description,pathOfFile,"onaylanmadi",newFile.getContentType());
            Files.write(path,bytes);
            datasetRepo.insert(newDataRequestToDb);



        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Hata alırsan 29'a bak.");
        }
        return true;
    }
    public boolean checkLogin(String credentials) {
        String[] arrOfStr = credentials.split(" ",2);
        String usernameInput = arrOfStr[0];
        String passwordInput = arrOfStr[1];
        boolean loggedIn = false;
        List<Admins> adminsList = adminsRepo.findAll();
        for(Admins admin:adminsList)
        {
            if (admin.getUsername().equals(usernameInput))
            {
                if(admin.getPassword().equals(passwordInput)){
                    loggedIn = true;
                    break;
                }
            }
        }
        return loggedIn;
    }

    public boolean addNotice(Notice notice){
        notice.setDateOfPublish(LocalDate.now());
        Notice noticeResponse = noticeRepo.insert(notice);
        if(noticeResponse != null) {
            return true;
        }
        else {
            return false;
        }

    }
    public boolean adminAddDataset(MultipartFile newFile, String newInfo)  {

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
            String description = null;

            for(int i =0;i<infos.length;i++) {
                String[] temp = infos[i].split(":");
                if(i==3) {
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
                else if (temp[0].contains("description"))
                {
                    description = temp[1];
                    description = description.replace("{","");
                    description = description.replace("}","");
                    description = description.replace("\"","");
                }
            }
            System.out.println(senderName);
            System.out.println(senderEmail);
            Dataset adminDataset = new Dataset(senderName,senderEmail,description,pathOfFile,"onaylandi",newFile.getContentType());
            Files.write(path,bytes);
            datasetRepo.insert(adminDataset);



        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Hata alırsan 29'a bak.");
        }
        return true;
    }
    public boolean updateNewDataRequestStatus(String paramId) {
        Optional<Dataset> oldVersion = datasetRepo.findById(paramId);

        if(oldVersion.isEmpty()) {
            return false;
        }

        else {
            Dataset newVersion = oldVersion.get();
            newVersion.setOnaylandiMi("onaylandi");
            datasetRepo.save(newVersion);
            return true;
        }


    }

    public boolean deleteDataRequestFromDb(String paramId) {

        Optional<Dataset> response = datasetRepo.findById(paramId);

        if(response.isEmpty()) {
            return false;
        }
        else {
            Dataset deleteThis = response.get();
            datasetRepo.delete(deleteThis);
            return true;
        }

    }

    public List<Dataset> getDataRequest() {

        List<Dataset> result = datasetRepo.getAllOnaylanmadi("onaylanmadi");

        if(result.isEmpty()) {
            System.out.println("dataRequestler alinamadi");
            return null;
        }
        else {
            return result;
        }

    }
    public List<Dataset> getDatasets(){
        List<Dataset> datasetList = datasetRepo.getAllOnaylandi();

        if(datasetList.isEmpty()) {
            System.out.println("datasetler alinamadi");
            return null;
        }
        else {
            return datasetList;
        }
    }
    public List<Integer> getAdminNumbers(){
        List<Integer> my_list = new ArrayList<>();
        List<Dataset> datasetList = datasetRepo.getAllOnaylandi();
        List<Dataset> result = datasetRepo.getAllOnaylanmadi("onaylanmadi");
        my_list.add(datasetList.size());
        Long x = noticeRepo.count();
        my_list.add(x.intValue());
        my_list.add(result.size());
        Long y = analysisRequestRepo.count();
        my_list.add(y.intValue());

        return my_list;
    }
}

