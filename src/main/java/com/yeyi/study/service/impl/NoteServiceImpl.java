package com.yeyi.study.service.impl;

import com.yeyi.study.mapper.NoteMapper;
import com.yeyi.study.model.Note;
import com.yeyi.study.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired(required = false)
    private NoteMapper noteMapper;

    @Override
    public int addNote(Note note) {
        return noteMapper.insert(note);
    }

}
