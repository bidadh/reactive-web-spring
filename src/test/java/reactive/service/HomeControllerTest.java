package reactive.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.Assert.*;

/**
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @date 21/4/17 2:16 PM
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HomeControllerTest {

  private WebTestClient webTestClient;

  @Before
  public void before() {
    this.webTestClient = WebTestClient.bindToApplicationContext()
  }

  @Test
  public void eventById() throws Exception {
    this.webTestClient.get()
        .uri("events/42")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()
        .expectStatus().isOk();
  }

  @Test
  public void events() throws Exception {
  }

}