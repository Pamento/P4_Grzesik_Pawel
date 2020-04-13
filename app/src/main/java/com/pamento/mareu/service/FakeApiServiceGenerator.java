package com.pamento.mareu.service;

import com.pamento.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FakeApiServiceGenerator {

    static List<Meeting> generateMeetings() { return new ArrayList<>(FAKE_MEETINGS); }

    private static List<Meeting> FAKE_MEETINGS = Arrays.asList(
            new Meeting(001,"Acceuil","14/04/2020 16h00", "Salle A", Arrays.asList("acceuil@entre.fr","pdg@entre.fr"), "hall_a"),
            new Meeting(002,"Acceuil","14/04/2020 16h00", "Salle A", Arrays.asList("acceuil@entre.fr","pdg@entre.fr"), "hall_a"),
            new Meeting(003,"Resourcee","07/04/2020 10h00", "Salle B", Arrays.asList("acceuil@entre.fr","pdg@entre.fr"), "hall_b"),
            new Meeting(004,"Resourcee","07/04/2020 10h00", "Salle B", Arrays.asList("acceuil@entre.fr","pdg@entre.fr"), "hall_b"),
            new Meeting(005,"Management","21/04/2020 11h30", "Salle C", Arrays.asList("acceuil@entre.fr","pdg@entre.fr"), "hall_c"),
            new Meeting(006,"Management","21/04/2020 11h30", "Salle C", Arrays.asList("acceuil@entre.fr","pdg@entre.fr"), "hall_c")
    );
}
