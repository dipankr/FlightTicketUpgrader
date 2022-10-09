package org.sahaj.data.enums;

public enum ValidatorResponse {
    All_VALID(""),
    CABIN_INVALID("Cabin invalid"),
    TRAVEL_DATE_INVALID("Travel date invalid"),
    EMAIL_INVALID("Email invalid"),
    PHONE_INVALID("Phone invalid"),
    PNR_INVALID("PNR invalid");

    private String message;

    public String getMessage() {
        return message;
    }

    ValidatorResponse(String message) {
        this.message = message;
    }
}
