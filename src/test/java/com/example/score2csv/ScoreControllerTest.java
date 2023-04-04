package com.example.score2csv;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreControllerTest {

    String fileName = "test.csv";
    ScoreController scoreController;

    @BeforeEach
    void setUp() throws IOException {
        scoreController = new ScoreController(fileName);
    }

    @AfterEach
    void tearDown() {
        new File(fileName).delete();
    }

    @Test
    void shouldStoreMultipleLines() throws Exception {
        Input input = new Input(LocalDateTime.now(), "fred", "fred@abc.com", 3);
        scoreController.score(input);
        Input input2 = new Input(LocalDateTime.now(), "klaas", "klaas@abc.com", 4);
        scoreController.score(input2);

        assertEquals(2, Files.readAllLines(new File(fileName).toPath()).size());
    }

    @Test
    void shouldNotStoreTheSameNameTwice() throws Exception {
        Input input = new Input(LocalDateTime.now(), "fred", "fred@abc.com", 3);
        scoreController.score(input);
        scoreController.score(input);

        assertEquals(1, Files.readAllLines(new File(fileName).toPath()).size());
    }
}