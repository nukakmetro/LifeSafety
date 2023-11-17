package com.example.lifesafetysem.essence;

import java.sql.Date;
import java.time.LocalDateTime;

public class User {
    private long id;
    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private String birthData;
    private LocalDateTime registratioDate;
    private long photo_id;
    private String description;

    public long getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(long photo_id) {
        this.photo_id = photo_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User(long aLong, String login, String password, String string, String resultSetString, Date date, LocalDateTime data, long resultSetLong) {
        this.registratioDate = data;
        this.login = login;
        this.password = password;
    }

    public User() {}

    public User(long id, String login, String password, String firstName, String secondName, String birthData, LocalDateTime registratioDate, long photoId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthData = birthData;
        this.registratioDate = registratioDate;
        this.photo_id = photoId;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getBirthData() {
        return birthData;
    }

    public void setBirthData(String birthData) {
        this.birthData = birthData;
    }

    public LocalDateTime getRegistratioDate() {
        return registratioDate;
    }

    public void setRegistratioDate(LocalDateTime registratioDate) {
        this.registratioDate = registratioDate;
    }
}
