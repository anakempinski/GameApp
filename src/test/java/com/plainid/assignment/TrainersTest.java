package com.plainid.assignment;

import com.plainid.assignment.dao.PokemonList;
import com.plainid.assignment.dao.TrainersList;
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
public class TrainersTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testTrainers() {
        String actualSortedTrainers = restTemplate.getForObject("http://localhost:" + port + "/trainers",
                                                                  String.class);


        String expectedSortedTrainers = "[{\"name\":\"Alex\", \"level\":\"0\", \"bag\":[\"Kingler\",\"Chikorita\"]}," +
                "{\"name\":\"Emma\", \"level\":\"1\", \"bag\":[\"Charizard\",\"Golduck\",\"Victreebel\"]}," +
                "{\"name\":\"James\", \"level\":\"1\", \"bag\":[\"Magikarp\",\"Cyndaquil\",\"Sunflora\"]}," +
                "{\"name\":\"John\", \"level\":\"2\", \"bag\":[\"Vulpix\",\"Vaporeon\",\"Fennekin\"]}," +
                "{\"name\":\"Olivia\", \"level\":\"2\", \"bag\":[\"Bulbasaur\",\"Sharpedo\"]}," +
                "{\"name\":\"Ben\", \"level\":\"3\", \"bag\":[\"Magmar\",\"Lapras\"]}]";


        assertEquals(expectedSortedTrainers, actualSortedTrainers);


    }
}