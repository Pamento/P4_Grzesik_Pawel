package com.pamento.mareu.service;

import com.pamento.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class FakeApiServiceGenerator {

    static List<Meeting> generateMeetings() { return new ArrayList<>(FAKE_MEETINGS); }

    private static List<Meeting> FAKE_MEETINGS = Arrays.asList(
            new Meeting(100,"Acceuil","14/04/2020", "16:00", "17:00","hall_a", Arrays.asList("one@lamzone.fr","two@lamzone.com","tree@lamzone.com")),
            new Meeting(200,"Messages","14/04/2020", "16:00", "17:00","hall_f", Arrays.asList("one@lamzone.fr","two@lamzone.com","tree@lamzone.com")),
            new Meeting(300,"Management","14/04/2020", "16:00", "17:00","hall_g", Arrays.asList("one@lamzone.fr","two@lamzone.com","tree@lamzone.com")),
            new Meeting(400,"Stand Up Meeting","14/04/2020", "16:00", "17:00","hall_c", Arrays.asList("one@lamzone.fr","two@lamzone.com","tree@lamzone.com")),
            new Meeting(500,"Resource","14/04/2020", "16:00", "17:00","hall_j", Arrays.asList("one@lamzone.fr","two@lamzone.com","tree@lamzone.com")),
            new Meeting(600,"Human Resource","14/04/2020", "16:00", "17:00","hall_i", Arrays.asList("acceuil@lamzone.fr","two@lamzone.com","tree@lamzone.com"))
    );
}
