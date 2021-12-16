package com.plainid.assignment;

import com.plainid.assignment.controller.TrainerController;
import com.plainid.assignment.dao.PokemonList;
import com.plainid.assignment.dao.Trainer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @RunWith(SpringRunner.class)
    public class TrainerTest {

        @LocalServerPort
        private int port;

        @Autowired
        private TestRestTemplate restTemplate;




    @Test
        public void testTrainerData(){
            String actualTrainer = restTemplate.getForObject("http://localhost:" + port + "/trainer/John",
                    String.class);

            String expectedTrainer = "{\"name\":\"John\", \"level\":\"2\", \"bag\":[\"Vulpix\",\"Vaporeon\",\"Fennekin\"]}";

            assertEquals(expectedTrainer, actualTrainer);

        }

}

