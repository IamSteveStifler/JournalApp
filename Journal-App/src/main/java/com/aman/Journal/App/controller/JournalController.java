package com.aman.Journal.App.controller;


import com.aman.Journal.App.entity.Journal;
import com.aman.Journal.App.entity.User;
import com.aman.Journal.App.service.impl.JournalServiceImpl;
import com.aman.Journal.App.service.impl.UserServiceImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/journal")
public class JournalController {

    @Autowired
    private JournalServiceImpl journalService;

    @PostMapping("/save")
    public ResponseEntity<Boolean> saveJournal(@RequestBody Journal journal,
                                               @RequestHeader("Username") String userName) {
        return new ResponseEntity<>(journalService.saveJournal(journal, userName), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Journal>> getAllJournalOfUser(@RequestHeader("Username") String userName) {
        return new ResponseEntity<>(journalService.getAllJournalOfUser(userName), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Journal> getJournalById(@PathVariable ObjectId id) {
        return new ResponseEntity<>(journalService.getJournalById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Journal> updateJournalById(@PathVariable ObjectId id,
                                                     @RequestBody Journal journal) {
        return new ResponseEntity<>(journalService.updateJournalById(id, journal), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteJournalById(@PathVariable ObjectId id,
                                                     @RequestHeader("Username") String userName) {
        return new ResponseEntity<>(journalService.deleteJournalById(id, userName), HttpStatus.OK);
    }

}
