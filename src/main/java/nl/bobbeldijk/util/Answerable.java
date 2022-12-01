package nl.bobbeldijk.util;

import java.util.List;

public interface Answerable<T> {
    T calculateAnswer(List<String> input) throws AnswerNotFoundException;
}
