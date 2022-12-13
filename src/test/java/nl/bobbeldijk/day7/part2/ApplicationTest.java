package nl.bobbeldijk.day7.part2;

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
        var testInput = InputReader.readStreamFromInputFile(InputFile.DAY7, true, "test")
                .collect(Collectors.toList());

        assertEquals(24933642, application.calculateAnswer(testInput));
    }
}
