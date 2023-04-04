package com.example.score2csv;

import java.time.LocalDateTime;

/**
 * {"datetime": "2023-04-01T11:08:24.923475", "username": "thijs", "email": "thijs@abc.com", "score": 7}
 */
public record Input(
        LocalDateTime datetime,
        String username,
        String email,
        int score
) {

    public Input(
            LocalDateTime datetime,
            String username,
            String email,
            int score
    ) {
        this.datetime = datetime;
        this.username = username.replace(",", "");
        this.email = email.replace(",", "");;
        this.score = score;
    }
}
