package com.example.tezspringbe;


import com.example.tezspringbe.models.AnalysisRequest;
import com.example.tezspringbe.models.Contact;
import com.example.tezspringbe.models.Notice;
import com.example.tezspringbe.repos.NoticeRepo;
import com.example.tezspringbe.services.NoticeService;
import lombok.AllArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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


//    SILME SAKIN DAHA SERVIS YAZMADIM BURAYA BIR DAHA UGRASTIRMA BENI



}



