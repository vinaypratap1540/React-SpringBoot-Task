package com.esd.moviebooking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(AssessmentTestWatcher.class)
@Sql(scripts = "/assessment-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class MovieBookingAssessmentTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Task 1: Create a booking for a valid movie and customer")
    void createBookingSuccessfully() throws Exception {
        String request = """
                {
                    "movieId": 1,
                    "customerId": 1,
                    "seatsBooked": 2
                }
                """;

        MvcResult result = mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andReturn();

        int responseStatus = result.getResponse().getStatus();
        String responseBody = result.getResponse().getContentAsString();

        assertTrue(
                responseStatus == 200 || responseStatus == 201,
                "Expected: booking should be created successfully (HTTP 200 or 201)\n"
                        + "Actual: HTTP " + responseStatus + "\n"
                        + "Response: " + responseBody);

        assertTrue(
                responseBody.contains("\"movieId\":1"),
                "Expected: response should contain the requested movie\n"
                        + "Actual: response did not contain movieId 1");

        assertTrue(
                responseBody.contains("\"customerId\":1"),
                "Expected: response should contain the requested customer\n"
                        + "Actual: response did not contain customerId 1");

        assertTrue(
                responseBody.contains("\"seatsBooked\":2"),
                "Expected: response should contain the requested number of seats\n"
                        + "Actual: response did not contain seatsBooked 2");

        assertTrue(
                responseBody.contains("\"status\":\"CONFIRMED\""),
                "Expected: new booking should have status CONFIRMED\n"
                        + "Actual: response did not contain status CONFIRMED");
    }

    @Test
    @DisplayName("Task 1: Reject a booking when the requested movie does not exist")
    void rejectMissingMovie() throws Exception {
        String request = """
                {
                    "movieId": 999,
                    "customerId": 1,
                    "seatsBooked": 2
                }
                """;

        int responseStatus = mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(
                404,
                responseStatus,
                "Expected: missing movie should be rejected with HTTP 404\n"
                        + "Actual: HTTP " + responseStatus);
    }

    @Test
    @DisplayName("Task 1: Reject a booking when the requested customer does not exist")
    void rejectMissingCustomer() throws Exception {
        String request = """
                {
                    "movieId": 1,
                    "customerId": 999,
                    "seatsBooked": 2
                }
                """;

        int responseStatus = mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(
                404,
                responseStatus,
                "Expected: missing customer should be rejected with HTTP 404\n"
                        + "Actual: HTTP " + responseStatus);
    }

    @Test
    @DisplayName("Task 1: Reject a request when required input is missing")
    void rejectMissingRequiredInput() throws Exception {
        String request = """
                {
                    "movieId": 1,
                    "customerId": 1
                }
                """;

        int responseStatus = mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(
                400,
                responseStatus,
                "Expected: missing required input should be rejected with HTTP 400\n"
                        + "Actual: HTTP " + responseStatus);
    }

    @Test
    @DisplayName("Task 2: Do not allow an already cancelled booking to be cancelled again")
    void rejectCancellingAlreadyCancelledBooking() throws Exception {
        int responseStatus = mockMvc.perform(put("/api/bookings/2/cancel"))
                .andReturn()
                .getResponse()
                .getStatus();

        assertTrue(
                responseStatus >= 400 && responseStatus < 500,
                "Expected: cancelling an already cancelled booking should be rejected\n"
                        + "Actual: request was accepted with HTTP " + responseStatus);
    }

    @Test
    @DisplayName("Task 3: Return only movies matching the requested genre and language")
    void returnOnlyMoviesMatchingGenreAndLanguage() throws Exception {
        String response = mockMvc.perform(get("/api/movies/search")
                        .param("genre", "ACTION")
                        .param("language", "ENGLISH"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(
                2,
                countOccurrences(response, "\"genre\":\"ACTION\""),
                "Expected: exactly two ACTION movies should match the search\n"
                        + "Actual: response did not contain exactly two ACTION movies");

        assertEquals(
                2,
                countOccurrences(response, "\"language\":\"ENGLISH\""),
                "Expected: exactly two ENGLISH movies should match the search\n"
                        + "Actual: response did not contain exactly two ENGLISH movies");

        assertTrue(
                !response.contains("\"title\":\"Warrior\""),
                "Expected: movies with a different language should not be returned\n"
                        + "Actual: Warrior was returned");

        assertTrue(
                !response.contains("\"title\":\"The Intern\""),
                "Expected: movies with a different genre should not be returned\n"
                        + "Actual: The Intern was returned");
    }

    private long countOccurrences(String text, String target) {
        long count = 0;
        int index = 0;

        while ((index = text.indexOf(target, index)) >= 0) {
            count++;
            index += target.length();
        }

        return count;
    }
}
