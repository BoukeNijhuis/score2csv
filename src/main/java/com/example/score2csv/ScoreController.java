package com.example.score2csv;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
public class ScoreController {

    private final String fileLocation;

    public ScoreController(String fileLocation) throws IOException {
        this.fileLocation = fileLocation;
    }

    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @PostMapping("/score")
    public String score(@RequestBody Input input) throws IOException {

        // check if the file contains a line starting with the username
        File file = new File(fileLocation);

        if (doesUsernameExistInFile(input, file))
        {
            System.err.println("Username '" + input.username() + "' already exists in the file (" + input + ")");
            return "Username already exists";
        }

        // username does not exist, so add a line to the file
        FileWriter fileWriter = new FileWriter(file, true);
        String line = String.format("%s, %s, %s, %s%s", input.username(), input.score(), input.email(),
                input.datetime(), System.lineSeparator());
        fileWriter.append(line);
        fileWriter.close();

        return input.toString();
    }

    private boolean doesUsernameExistInFile(Input input, File file) throws IOException {
        if (file.exists()) {
            List<String> strings = Files.readAllLines(file.toPath());
            for (String string : strings) {
                if (string.startsWith(input.username())) {
                    return true;
                }
            }
        }
        return false;
    }

}
