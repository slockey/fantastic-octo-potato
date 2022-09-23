package com.slockey.fantasticoctopotato.controller;

import com.slockey.fantasticoctopotato.stream.PotatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PotatoController {

    private final PotatoRepository potatoRepository;

    @Autowired
    public PotatoController(PotatoRepository potatoRepository) {
        this.potatoRepository = potatoRepository;
    }


}
