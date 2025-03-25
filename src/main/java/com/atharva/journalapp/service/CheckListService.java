package com.atharva.journalapp.service;

import com.atharva.journalapp.entity.CheckList;
import com.atharva.journalapp.entity.JournalEntry;
import com.atharva.journalapp.entity.User;
import com.atharva.journalapp.repository.CheckListRepo;
import com.atharva.journalapp.repository.JournalEntryRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CheckListService {

    @Autowired
    private CheckListRepo checkListRepo;

    @Autowired
    private UserService userService;


    @Transactional
    public void saveCheckList(CheckList checkList, String userName) {
        try {
            User user = userService.findByUserName(userName);
            CheckList saved = checkListRepo.save(checkList);
            user.getCheckList().add(saved);
            userService.saveUser(user);
        }
        catch (Exception e) {
            log.error("Error: ",e);
            throw new RuntimeException("An error occurred while saving checkList", e);
        }

    }
    public void saveCheckList(CheckList checklist) {
        checkListRepo.save(checklist);

    }
    public List<CheckList> getAll() {
        return checkListRepo.findAll();
    }
    public Optional<CheckList> getById(ObjectId id) {
        return checkListRepo.findById(id);
    }
//    @Transactional
//    public boolean deleteById(ObjectId id, String userName) {
//        boolean removed = false;
//        try {
//            User user = userService.findByUserName(userName);
//            removed = user.getJournalEntry().removeIf(x -> x.getId().equals(id));
//            if (removed) {
//                userService.saveUser(user);
//                checkListRepo.deleteById(id);
//            }
//
//        }
//        catch (Exception e) {
//            System.out.println(e);
//            throw new RuntimeException("An error occurred while deleting journal entry", e);
//        }
//        return removed;
//    }
//    public List<JournalEntry> findByUserName(Long userId) {}
}
