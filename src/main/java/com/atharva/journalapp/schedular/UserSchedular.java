package com.atharva.journalapp.schedular;

import com.atharva.journalapp.cache.AppCache;
import com.atharva.journalapp.entity.JournalEntry;
import com.atharva.journalapp.entity.User;
import com.atharva.journalapp.repository.UserRepoImpl;
import com.atharva.journalapp.service.EmailService;
import com.atharva.journalapp.service.SentimentAnalysisService;
import com.atharva.journalapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserSchedular {
    @Autowired
    private EmailService emailService;

    @Autowired
    private AppCache appCache;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private UserRepoImpl userRepoImpl;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUserAndSendSAEmail(){
        List<User> userForSA = userRepoImpl.getUserForSA("abc");
        for(User user : userForSA){
            List<JournalEntry> journalEntry = user.getJournalEntry();
            List<String> filteredEntries = journalEntry.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getContent()).collect(Collectors.toList());
            String entry = String.join(" ", filteredEntries);
            String sentiment = sentimentAnalysisService.getSentiment(entry);
            emailService.sendEmail(user.getEmail(),"Sentiment for the last 7 days", sentiment);
        }

    }
    @Scheduled(cron = "0 */10 * * * *")
    public void clearAppCache(){
        appCache.init();
    }
}
