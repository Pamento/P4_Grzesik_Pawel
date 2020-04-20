package com.pamento.mareu.service;

public abstract class Resources {

    private static String[] emailContacts = new String[] {
            "director@lamzone.com",
            "manager@lamzone.com",
            "secreatary@lamzone.com",
            "rh@lamzone.com",
            "depot@lamzone.com",
            "archive@lamzone.com",
            "reserch@lamzone.com",
            "study@lamzone.com",
            "analize@lamzone.com",
            "callback@lamzone.com",
            "viceDirector@lamzone.com"
    };

    public static String[] getEmailContacts() {
        return emailContacts;
    }
}
