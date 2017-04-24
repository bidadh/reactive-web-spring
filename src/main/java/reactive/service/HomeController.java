package reactive.service;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactive.Event;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;

import static java.util.stream.Stream.generate;
import static reactor.core.publisher.Flux.fromStream;
import static reactor.core.publisher.Flux.interval;
import static reactor.core.publisher.Flux.zip;

/**
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @date 21/4/17 11:34 AM
 */
@RestController
public class HomeController {
  @GetMapping("/events/{id}")
  Mono<Event> eventById(@PathVariable long id) {
    return Mono.just(Event.of(id, new Date()));
  }

  @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE, value = "/events")
  Flux<Event> events() {
    Flux<Event> eventFlux = fromStream(generate(() -> Event.of(System.currentTimeMillis(), new Date())));
    Flux<Long> durationFlux = interval(Duration.ofSeconds(1));
    return zip(eventFlux, durationFlux).map(Tuple2::getT1);
  }
}
