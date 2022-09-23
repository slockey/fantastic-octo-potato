package com.slockey.fantasticoctopotato.stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.slockey.fantasticoctopotato.message.Potato;
import org.junit.jupiter.api.Test;

public class PotatoRepositoryTest {

    private PotatoRepository repository;

    @Test
    public void testGetPotato() throws Exception {
        repository = new PotatoRepository();

        Potato firstPotato = repository.getPotato();
        assertEquals("Title value: 1", firstPotato.getTitle());
        assertEquals("Body value: 1", firstPotato.getBody());

        Potato secondPotato = repository.getPotato();
        assertEquals("Title value: 2", secondPotato.getTitle());
        assertEquals("Body value: 2", secondPotato.getBody());

    }

}
