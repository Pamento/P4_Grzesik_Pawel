package com.pamento.mareu.service;

import com.pamento.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FakeApiServiceGenerator {

    static List<Meeting> generateMeetings() { return new ArrayList<>(FAKE_MEETINGS); }

    private static List<Meeting> FAKE_MEETINGS = Arrays.asList(
            new Meeting(001,"Acceuil","14/04/2020 16h00", "Salle A", Arrays.asList("acceuil@entre.fr","pdg@entre.fr")),
            new Meeting(001,"Resourcee","07/04/2020 10h00", "Salle B", Arrays.asList("acceuil@entre.fr","pdg@entre.fr")),
            new Meeting(001,"Management","21/04/2020 11h30", "Salle C", Arrays.asList("acceuil@entre.fr","pdg@entre.fr"))
    );
}
