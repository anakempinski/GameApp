package com.plainid.assignment;

import com.plainid.assignment.dao.PokemonList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CatchTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCatch() {
        String actualBag = restTemplate.getForObject("http://localhost:" + port + "trainer/John/catch/Magmar",
                                                        String.class);

        String expectedBag = "{\"bag\":[\"Vaporeon\",\"Fennekin\",\"Magmar\"]}";

        assertEquals(expectedBag, actualBag);

    }
}
