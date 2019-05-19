package com.yeyi.study.model;

import java.util.Date;

public class Note {
    private Integer id;

    private Date studyDate;

    private String lessonNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStudyDate() {
        return studyDate;
    }

    public void setStudyDate(Date studyDate) {
        this.studyDate = studyDate;
    }

    public String getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(String lessonNumber) {
        this.lessonNumber = lessonNumber == null ? null : lessonNumber.trim();
    }
}