package com.telstra.codechallenge;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.telstra.codechallenge.service.AccountService;
import com.telstra.codechallenge.service.RepositoryService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MicroServiceMainTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;
  

  @Test
  public void testHealth() throws RestClientException, MalformedURLException {
    ResponseEntity<String> response = restTemplate
        .getForEntity(new URL("http://localhost:" + port + "/actuator/health")
            .toString(), String.class);
    assertEquals("{\"status\":\"UP\"}", response
        .getBody());
  }
  
  
  @Test
  public void testgetHottestRepositories() throws RestClientException, MalformedURLException {
    ResponseEntity<String> response = restTemplate
        .getForEntity(new URL("http://localhost:" + port + "/hottest/repositories?count=0")
            .toString(), String.class);
    assertEquals("[]", response
        .getBody());
  }
  
  @Test
  public void testOldestRepositories() throws RestClientException, MalformedURLException {
    ResponseEntity<String> response = restTemplate
        .getForEntity(new URL("http://localhost:" + port + "/oldest/accounts?count=0")
            .toString(), String.class);
    assertEquals("[]", response
        .getBody());
  }
  
  
  @Test
  public void getHottestRepositories() throws RestClientException, MalformedURLException {
    ResponseEntity<String> response = restTemplate
        .getForEntity(new URL("http://localhost:" + port + "/hottest/repositories?count=zyz")
            .toString(), String.class);
    assertEquals(400, response
        .getStatusCodeValue());
  }
  
  @Test
  public void OldestRepositories() throws RestClientException, MalformedURLException {
    ResponseEntity<String> response = restTemplate
        .getForEntity(new URL("http://localhost:" + port + "/oldest/accounts?count=xyz")
            .toString(), String.class);
    assertEquals(400, response
            .getStatusCodeValue());
  }
}
