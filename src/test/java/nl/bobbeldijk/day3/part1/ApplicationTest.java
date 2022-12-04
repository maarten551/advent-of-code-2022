package nl.bobbeldijk.day3.part1;

import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ApplicationTest {

    private Application application;

    @BeforeEach
    void setUp() {
        application = new Application();
    }

    @Test
    void calculateAnswerTest() throws AnswerNotFoundException {
        var testInput = InputReader.readStreamFromInputFile(InputFile.DAY3, true, "test")
                .collect(Collectors.toList());

        assertEquals(157, application.calculateAnswer(testInput));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/day-3/calculateAnswerSingleLinesTest.csv")
    void calculateAnswerSingleLinesTest(String line, Integer expectedValue) throws AnswerNotFoundException {
        assertEquals(expectedValue, application.calculateAnswer(List.of(line)));
    }
}