package com.pamento.mareu.service;

import com.pamento.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FakeApiServiceGenerator {

    static List<Meeting> generateMeetings() { return new ArrayList<>(FAKE_MEETINGS); }

    public static List<Meeting> FAKE_MEETINGS = Arrays.asList(
            new Meeting(100,"Accueil","14/05/2020", "16:00", "17:00","hall_a", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(200,"Messages","14/05/2020", "16:00", "17:00","hall_f", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(300,"Management","13/05/2020", "16:00", "17:00","hall_g", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(400,"Stand Up Meeting","14/05/2020", "10:00", "10:45","hall_c", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(500,"Ressources","13/05/2020", "16:00", "17:00","hall_b", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(600,"Human Resource","14/05/2020", "16:00", "17:00","hall_i", Arrays.asList("acceuil@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(700,"Messages","13/05/2020", "15:00", "16:00","hall_f", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(800,"Management","105/05/2020", "16:00", "17:00","hall_g", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(900,"Stand Up Meeting","13/05/2020", "11:00", "12:00","hall_c", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(110,"Ressources","105/05/2020", "16:00", "17:00","hall_b", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(120,"Human Resource","14/05/2020", "16:00", "17:00","hall_i", Arrays.asList("acceuil@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(130,"Messages","14/05/2020", "14:00", "15:00","hall_f", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(140,"Management","16/05/2020", "16:00", "17:00","hall_g", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(1050,"Stand Up Meeting","14/05/2020", "14:00", "15:00","hall_c", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(160,"Ressources","16/05/2020", "16:00", "17:00","hall_b", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(170,"Human Resource","14/05/2020", "16:00", "17:00","hall_i", Arrays.asList("acceuil@lamzone.com","two@lamzone.com","tree@lamzone.com"))
    );
}
