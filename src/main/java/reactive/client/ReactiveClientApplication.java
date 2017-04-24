package reactive.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactive.Event;

import java.util.Collections;

/**
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @date 21/4/17 1:52 PM
 */
@SpringBootApplication
public class ReactiveClientApplication {

  @Bean
  WebClient client() {
    return WebClient.create("http://localhost:8080");
  }

  @Bean
  CommandLineRunner demo(WebClient client) {
    return args -> {
      client.get().uri("/events")
          .accept(MediaType.APPLICATION_STREAM_JSON)
          .exchange()
          .flatMapMany(cr -> cr.bodyToFlux(Event.class))
          .subscribe(System.out::println);
    };
  }

  public static void main(String[] args) {
    new SpringApplicationBuilder(ReactiveClientApplication.class)
        .properties(Collections.singletonMap("server.port", "8081"))
        .run(args);
  }
}
