package com.slockey.fantasticoctopotato.controller;

import com.slockey.fantasticoctopotato.message.Potato;
import com.slockey.fantasticoctopotato.stream.PotatoRepository;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.core.publisher.Flux;

@RequestMapping("/api")
@RestController
public class PotatoController {

    private final PotatoRepository potatoRepository;

    @Autowired
    public PotatoController(PotatoRepository potatoRepository) {
        this.potatoRepository = potatoRepository;
    }

    @GetMapping(value = "/potato", produces = MediaType.APPLICATION_JSON_VALUE)
    public Potato getNextPotato() {
        return this.potatoRepository.getPotato();
    }

    @GetMapping(value = "/potatoStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Potato> getStreamedPotato() {
        return Flux.interval(Duration.ofMillis(200))
                .map(tick -> this.potatoRepository.getPotato());
    }

    @GetMapping("/otherPotatoStream")
    public ResponseEntity<StreamingResponseBody> getOtherPotatoStream() {
        StreamingResponseBody responseBody = response -> {
            while(Thread.currentThread().isAlive()) {
                    String msg = this.potatoRepository.getPotato().toString() + "\n";
                    response.write(msg.getBytes(StandardCharsets.UTF_8));
                    response.flush();
            }
        };
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responseBody);
    }
}
