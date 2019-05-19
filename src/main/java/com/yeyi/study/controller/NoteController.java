package com.yeyi.study.controller;

import com.yeyi.study.model.Note;
import com.yeyi.study.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @RequestMapping("toAddNote")
    public String toAddNote() {
        return "addNote";
    }

    @RequestMapping("save")
    public void save(Note note) {
        noteService.addNote(note);
    }


}
