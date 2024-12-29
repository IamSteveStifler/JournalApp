package com.aman.Journal.App.service.impl;

import com.aman.Journal.App.entity.Journal;
import com.aman.Journal.App.entity.User;
import com.aman.Journal.App.repository.JournalRepository;
import com.aman.Journal.App.repository.UserRepository;
import com.aman.Journal.App.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository journalRepository;

//    @Autowired
//    private UserRepository userRepository;

    @Override
    public boolean saveJournal(Journal journal) {
//        User userInDb = userRepository.findByUserName(userName);
//        if(userInDb == null) return false;
        journal.setDate(LocalDateTime.now());
//        userInDb.getJournals().add(journal);
//        userRepository.save(userInDb);
        journalRepository.save(journal);
        return true;
    }

    @Override
    public List<Journal> getAllJournal() {
        return journalRepository.findAll();
    }

    @Override
    public Journal getJournalById(ObjectId id) {
        return journalRepository.findById(id).orElse(null);
    }

    @Override
    public Journal updateJournalById(ObjectId id, Journal updatedJournal) {
        Journal old = journalRepository.findById(id).orElse(null);
        if(old != null) {
            old.setTitle((updatedJournal != null && updatedJournal.getTitle() != null) ? updatedJournal.getTitle(): old.getTitle());
            old.setDescription((updatedJournal != null && updatedJournal.getDescription() != null) ? updatedJournal.getDescription(): old.getDescription());
            return journalRepository.save(old);
        }
        return null;
    }

    @Override
    public Boolean deleteJournalById(ObjectId id) {
        Journal old = journalRepository.findById(id).orElse(null);
        if(old != null) {
            journalRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
