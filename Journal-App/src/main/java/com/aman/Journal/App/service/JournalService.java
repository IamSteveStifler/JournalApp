package com.aman.Journal.App.service;

import com.aman.Journal.App.entity.Journal;
import org.bson.types.ObjectId;

import java.util.List;

public interface JournalService {

    boolean saveJournal(Journal journal);
    List<Journal> getAllJournal();
    Journal getJournalById(ObjectId id);
    Journal updateJournalById(ObjectId id, Journal updatedJournal);
    Boolean deleteJournalById(ObjectId id);

}
