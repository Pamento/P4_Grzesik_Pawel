package com.pamento.mareu.service;

import com.pamento.mareu.R;
import com.pamento.mareu.model.Meeting;
import com.pamento.mareu.ui.newMeetingHallSpinner.HallItem;
import com.pamento.mareu.ui.newMeetingHourSpinner.Hour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Resources {

    static List<Meeting> mockingMeetings() { return new ArrayList<>(MOCKS_MEETINGS); }

    public static List<Meeting> MOCKS_MEETINGS = Arrays.asList(
            new Meeting(100,"Accueil","14/05/2020", "16:00", "17:00","hall_a", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(200,"Messages","14/05/2020", "16:00", "17:00","hall_f", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(300,"Management","13/05/2020", "16:00", "17:00","hall_g", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(400,"Stand Up Meeting","14/05/2020", "10:00", "10:45","hall_c", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(500,"Ressources","13/05/2020", "16:00", "17:00","hall_b", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(600,"Human Resource","14/05/2020", "16:00", "17:00","hall_i", Arrays.asList("acceuil@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(700,"Messages","13/05/2020", "15:00", "16:00","hall_f", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(800,"Management","15/05/2020", "16:00", "17:00","hall_g", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(900,"Stand Up Meeting","13/05/2020", "11:00", "12:00","hall_c", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(110,"Ressources","15/05/2020", "16:00", "17:00","hall_b", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(120,"Human Resource","14/05/2020", "16:00", "17:00","hall_i", Arrays.asList("acceuil@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(130,"Messages","14/05/2020", "14:00", "15:00","hall_f", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(140,"Management","16/05/2020", "16:00", "17:00","hall_g", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(150,"Stand Up Meeting","14/05/2020", "14:00", "15:00","hall_c", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(160,"Ressources","16/05/2020", "16:00", "17:00","hall_b", Arrays.asList("one@lamzone.com","two@lamzone.com","tree@lamzone.com")),
            new Meeting(170,"Human Resource","14/05/2020", "16:00", "17:00","hall_i", Arrays.asList("acceuil@lamzone.com","two@lamzone.com","tree@lamzone.com"))
    );

    public static List<HallItem> initHallSpinnerList() { return new ArrayList<>(HALL_LIST); }

    private static List<HallItem> HALL_LIST = Arrays.asList(
            new HallItem("hall_0", R.drawable.hall_0),
            new HallItem("hall_a", R.drawable.hall_a),
            new HallItem("hall_b", R.drawable.hall_b),
            new HallItem("hall_c", R.drawable.hall_c),
            new HallItem("hall_d", R.drawable.hall_d),
            new HallItem("hall_e", R.drawable.hall_e),
            new HallItem("hall_f", R.drawable.hall_f),
            new HallItem("hall_g", R.drawable.hall_g),
            new HallItem("hall_h", R.drawable.hall_h),
            new HallItem("hall_i", R.drawable.hall_i),
            new HallItem("hall_j", R.drawable.hall_j)
    );

    public static List<Hour> initHourSpinnerList() {
        return new ArrayList<>(HOUR_LIST);
    }

    private static List<Hour> HOUR_LIST = Arrays.asList(
            new Hour("l'heur"),
            new Hour("08:00"),
            new Hour("08:15"),
            new Hour("08:30"),
            new Hour("08:45"),
            new Hour("09:00"),
            new Hour("09:15"),
            new Hour("09:30"),
            new Hour("09:45"),
            new Hour("10:00"),
            new Hour("10:15"),
            new Hour("10:30"),
            new Hour("10:45"),
            new Hour("11:00"),
            new Hour("11:15"),
            new Hour("11:30"),
            new Hour("11:45"),
            new Hour("12:00"),
            new Hour("12:15"),
            new Hour("12:30"),
            new Hour("12:45"),
            new Hour("13:00"),
            new Hour("13:15"),
            new Hour("13:30"),
            new Hour("13:45"),
            new Hour("14:00"),
            new Hour("14:15"),
            new Hour("14:30"),
            new Hour("14:45"),
            new Hour("15:00"),
            new Hour("15:15"),
            new Hour("15:30"),
            new Hour("15:45"),
            new Hour("16:00"),
            new Hour("16:15"),
            new Hour("16:30"),
            new Hour("16:45"),
            new Hour("17:00")
    );
}
