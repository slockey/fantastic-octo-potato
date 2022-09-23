package com.slockey.fantasticoctopotato.stream;

import com.slockey.fantasticoctopotato.message.Potato;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

public class PotatoRepository {

    private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("UTC");

    private long number = 0;

    public Potato getPotato() {
        // update the number
        number += 1;

        Potato thePotato = new Potato();
        thePotato.setId(UUID.randomUUID().toString());
        thePotato.setTimestamp(Calendar.getInstance(TIME_ZONE).getTimeInMillis());
        thePotato.setTitle(String.format("Title value: %s", number));
        thePotato.setBody(String.format("Body value: %s", number));
        return thePotato;
    }

}
