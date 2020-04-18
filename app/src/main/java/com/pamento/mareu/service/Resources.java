package com.pamento.mareu.service;

import com.pamento.mareu.model.Meeting;

import java.util.Arrays;
import java.util.List;

public abstract class Resources {

    /**
     *
     * private ArrayList<String> groceryList = new ArrayList<String>();
     *
     * public void modifyGroceryItem(int position, String newItem) {
     * groceryList.set(position, newItem);
     * System.out.println("Grocery item " + (position+1) + " has been modified.");
     *
     *     public String findItem(String searchItem) {
     * //        boolean exists = groceryList.contains(searchItem);
     *
     *         int position = groceryList.indexOf(searchItem);
     *         if(position >=0) {
     *             return groceryList.get(position);
     *         }
     *
     *         return null;
     *     }
     }
     */
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
