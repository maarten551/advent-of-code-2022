package nl.bobbeldijk.day8.part2;

import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        var testInput = InputReader.readStreamFromInputFile(InputFile.DAY8, true, "test")
                .collect(Collectors.toList());

        assertEquals(8, application.calculateAnswer(testInput));
    }
}
