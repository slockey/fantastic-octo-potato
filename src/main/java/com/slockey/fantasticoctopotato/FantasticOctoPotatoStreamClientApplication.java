package com.slockey.fantasticoctopotato;

import com.slockey.fantasticoctopotato.stream.WebClientStreamingService;
import java.io.BufferedReader;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FantasticOctoPotatoStreamClientApplication {

    public static void main(String[] args) throws Exception {
        WebClientStreamingService service = new WebClientStreamingService();
        System.out.println("Starting...");

        BufferedReader bReader = service.connectToStream();
        if (bReader != null) {
            String message;
            while (!(message = service.getStreamMessage(bReader)).isEmpty()) {
                System.out.println("Got a message...");
                System.out.println(message);
            }
        }
        // close when we get an empty message
        service.closeStream();
        // show we're done
        System.out.println("Done");

////        SpringApplication.run(FantasticOctoPotatoStreamClientApplication.class, args);
//
//        HttpClient httpClient = HttpClient.create()
//                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
//                .responseTimeout(Duration.ofMillis(5000))
//                .doOnConnected( conn -> conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
//                                            .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));
//
//        WebClient webClient = WebClient.builder()
//                .clientConnector(new ReactorClientHttpConnector(httpClient))
//                .baseUrl("http://localhost:8080/potatoStream")
//                .filter(ExchangeFilterFunctions.basicAuthentication("user", "pass"))
//                .build();
//
//        Flux<Potato> potatoFlux = webClient.method(HttpMethod.GET).retrieve().bodyToFlux(Potato.class);
//        potatoFlux.doOnNext(potato -> {
//            System.out.println(potato.toString());
//        });
    }

}
