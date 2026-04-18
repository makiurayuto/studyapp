package com.example.studyapp;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int time;

    private LocalDate studyDate;

    public Study() {}

    // どっちでも使えるようにしておく（安全）
    public Study(int time, LocalDate studyDate) {
        this.time = time;
        this.studyDate = studyDate;
    }

    public Long getId() {
        return id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public LocalDate getStudyDate() {
        return studyDate;
    }

    public void setStudyDate(LocalDate studyDate) {
        this.studyDate = studyDate;
    }
}