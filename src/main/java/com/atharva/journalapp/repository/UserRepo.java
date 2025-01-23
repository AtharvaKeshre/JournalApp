package com.atharva.journalapp.repository;
import com.atharva.journalapp.entity.JournalEntry;
import com.atharva.journalapp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepo extends MongoRepository<User, ObjectId>{

    User findByUserName(String username);
    void deleteByUserName(String username);
}
