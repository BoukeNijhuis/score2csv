package com.example.score2csv;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Score2csvApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void helloTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello")).andDo(print())
                .andExpect(content().string("Hello"));
    }

    @Test
    void scoreTest() throws Exception {
        String postContent = "{\"datetime\": \"2023-04-01T11:08:24.923475\", \"username\": \"th,ijs\", \"email\": \"thij,s@abc.com\", \"score\": 7}\n";
        String expectedContent = "Input[datetime=2023-04-01T11:08:24.923475, username=thijs, email=thijs@abc.com, score=7]";
        mockMvc.perform(MockMvcRequestBuilders.post("/score").contentType(MediaType.APPLICATION_JSON).content(postContent)).andDo(print())
              .andExpect(status().isOk()).andExpect(content().string(expectedContent));
    }

}
