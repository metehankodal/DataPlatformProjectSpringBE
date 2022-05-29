package com.example.tezspringbe;


import com.example.tezspringbe.models.*;
import com.example.tezspringbe.repos.AdminsRepo;
import com.example.tezspringbe.repos.NoticeRepo;
import com.example.tezspringbe.services.NoticeService;
import lombok.AllArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/notice")
@AllArgsConstructor
public class NoticeController {

    private  NoticeService noticeService;

    @GetMapping("/getAllNotices")
    public List<Notice> fetchAllNotices() {
        return noticeService.getAllNotice();
    }

    @PostMapping("addContact")
    public boolean createContact(@RequestBody Contact contact) { return noticeService.createNewContact(contact);}

    @PostMapping("addAnalysisRequest")
    public boolean addAnalysisRequest(@RequestBody AnalysisRequest analysisRequest) { return noticeService.createNewAnalysisRequest(analysisRequest);}

    @PostMapping("newData")
    @ResponseBody
    public boolean addNewDataRequest(@RequestParam("files") MultipartFile file, @RequestParam("Info") String Info  ) {return noticeService.createNewDataRequest(file,Info);};

    @PostMapping("/checkLogin")
    public boolean checkLogs(@RequestBody String credentials) { return noticeService.checkLogin(credentials);}

    @PostMapping("addNotice")
    public boolean addNotice(@RequestBody Notice notice) { return noticeService.addNotice(notice);}

    @PostMapping("adminNewData")
    @ResponseBody
    public boolean adminAddDataset(@RequestParam("files") MultipartFile file, @RequestParam("Info") String Info  ) {return noticeService.adminAddDataset(file,Info);};

    @PostMapping("/updateNewDataRequestToDb")
    @ResponseBody
    public boolean updateNewDataRequestToDb(@RequestBody String paramId) { return noticeService.updateNewDataRequestStatus(paramId);};

    @DeleteMapping("/deleteDataRequest")
    public boolean deleteDataRequest(@RequestParam (value="paramId")  String paramId) { return noticeService.deleteDataRequestFromDb(paramId);};

    @GetMapping("getUnapprovedDataRequests")
    public List<Dataset> getDataRequest() {
        return noticeService.getDataRequest();
    }

    @GetMapping("getApprovedDatasets")
    public List<Dataset> getDatasets(){
        return noticeService.getDatasets();
    }

    @GetMapping("getAdminNo")
    public List<Integer> getNoOfAdmins(){
        return noticeService.getAdminNumbers();
    }

    @GetMapping("getApprovedDataSetsMapped")
    public List<ApprovedDataSet> getApprovedDataSetsMapped() {return noticeService.getAllApprovedDataSetsMapped();};

    @PostMapping("saveDataAnalysis")
    @ResponseBody
    public boolean saveDataAnalysis(@RequestParam("files") MultipartFile file, @RequestParam("Info") String Info) { return noticeService.saveDataAnalysisToDb(file,Info);};

    @GetMapping("getDataAnalysisRequests")
    public List<ApproveAnalysisRequestResponse> getDataAnalysisRequests(){return noticeService.getDataAnalysisRequests();}

    @PostMapping("saveDataAnalyisComeFromUser")
    @ResponseBody
    public boolean saveDataAnalyisComeFromUser(@RequestParam("files") MultipartFile file,@RequestParam("Info") String Info) throws IOException {return noticeService.saveDataAnalysisComeFromUser(file,Info);};

    @GetMapping("getDatasetById")
    public Optional<Dataset> getDatasetById(@RequestParam (value="datasetId")  String datasetId){return noticeService.getDatasetById(datasetId);}

    @GetMapping("getAnalysisReqList")
    public List<DatasetAnalysis> getAnalysisReqList(@RequestParam (value="datasetId")  String datasetId){return noticeService.getAnalysisReqList(datasetId);}


//    @Bean
//    CommandLineRunner runner(NoticeRepo repo) {
//        return args -> {
//            Notice notice = new Notice(LocalDate.now(),
//                    "Kurallar",
//                    "Sitede kişisel bilgi içeren veri setlerinin paylaşılması yasaktır. Böyle bir durum olması durumunda lütfen iletişim kısmında bizimle iletişime geçiniz.");
//            Notice notice1 = new Notice(LocalDate.now(),
//                    "Veri Seti Analizi İstekleri",
//                    "Veri seti analizi isteklerinizi veri seti analizi kısmından ayrıntılı olarak girebilirsiniz."
//                    );
//            repo.insert(notice);
//            repo.insert(notice1);
//        };
//    }
    /*@Bean
    CommandLineRunner runner(AdminsRepo repo) {
        return args -> {
            repo.insert(new Admins("admin","admin"));
        };
    }*/


//    SILME SAKIN DAHA SERVIS YAZMADIM BURAYA BIR DAHA UGRASTIRMA BENI



}




