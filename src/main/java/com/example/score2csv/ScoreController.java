package com.example.score2csv;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;

@RestController
public class ScoreController {

    @Value("${file.location}")
    String fileLocation;

    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @PostMapping("/score")
    public String score(@RequestBody Input input) throws IOException {

        FileWriter pw = new FileWriter(fileLocation, true);
        String line = String.format("%s, %s, %s, %s%s", input.username(), input.score(), input.email(),
                input.datetime(), System.lineSeparator());
        pw.append(line);
        pw.close();

        return input.toString();
    }

}
