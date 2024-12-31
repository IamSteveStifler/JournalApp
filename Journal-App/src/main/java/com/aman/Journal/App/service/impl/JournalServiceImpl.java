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

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean saveJournal(Journal journal, String userName) {
        User userInDb = userRepository.findByUserName(userName);
        if(userInDb == null) return false;
        journal.setDate(LocalDateTime.now());
        journalRepository.save(journal);
        userInDb.getJournals().add(journal);
        userRepository.save(userInDb);
        return true;
    }

    @Override
    public List<Journal> getAllJournalOfUser(String userName) {
        User userInDb = userRepository.findByUserName(userName);
        if(userInDb == null) return null;
        List<Journal> journals = userInDb.getJournals();
//        List<Journal> journalList = new ArrayList<>();
//        for(Journal journal: journals){
//            journalList.add(journalRepository.findById(journal.getId()).orElse(null));
//        }
//        return  journalList;
        return journals;

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
    public Boolean deleteJournalById(ObjectId id, String userName) {
        Journal old = journalRepository.findById(id).orElse(null);
        if(old != null) {
            User user = userRepository.findByUserName(userName);
            List<Journal> journalList = user.getJournals();
            journalList = journalList.stream().filter(journal -> !journal.getId().equals(id)).toList();
            journalRepository.deleteById(id);
            user.setJournals(journalList);
            userRepository.save(user);
            return true;
        }

        return false;
    }
}
