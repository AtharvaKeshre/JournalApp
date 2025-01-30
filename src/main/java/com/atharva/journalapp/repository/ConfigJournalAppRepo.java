package com.atharva.journalapp.repository;
import com.atharva.journalapp.entity.ConfigJournalAppEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface ConfigJournalAppRepo extends MongoRepository<ConfigJournalAppEntity, ObjectId>{


}
