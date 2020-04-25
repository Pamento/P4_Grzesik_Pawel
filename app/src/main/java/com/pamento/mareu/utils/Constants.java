package com.pamento.mareu.utils;

public abstract class Constants {
    public static final String FILTER = "filter";
    public static final String NEW_MEETING = "newMeeting";
    public static final String ADD_MEETING = "addMeeting";
    public static final String EXTRA_DATE_PICKER_DIALOG = "dateFromPickerDialog";
    public static final int TARGET_FRAGMENT_REQUEST_CODE = 1;

    /**
     * Message to communicate to the user
     */
    public static final String NO_MEETING_AT_DATE = "Il n'y a pas de réunion le ";
    public static final String NO_MEETING_IN_HALL  = "Il n'y a pas de réunion dans la salle: ";
    public static final String SUCCESS_ADD_MEETING = "La réunion a bien été enregistrée.";
    public static final String WARNING_SAME_HOURS = "Les heures sont identiques. C'est normal?.";
    public static final String ERROR_MESSAGE = "Une erreur est survenue. Essayez à nouveau.";
    public static final String ERROR_INVALID_EMAIL = "La forme d'e-mail n'est pas valid.";
}
