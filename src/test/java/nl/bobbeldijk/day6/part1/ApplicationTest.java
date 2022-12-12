package nl.bobbeldijk.day6.part1;

import nl.bobbeldijk.util.AnswerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ApplicationTest {

    private Application application;

    @BeforeEach
    void setUp() {
        application = new Application();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/day-6/test-input.csv")
    void calculateAnswerTest(String line, Integer expectedValue) throws AnswerNotFoundException {
        assertEquals(expectedValue, application.calculateAnswer(List.of(line)));
    }
}