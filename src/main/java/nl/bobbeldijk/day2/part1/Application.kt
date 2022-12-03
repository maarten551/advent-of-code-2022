package nl.bobbeldijk.day2.part1

import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY2))
}

enum class Shape(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);
}

class Application : Answerable<Int> {
    private val shapeToScoreMapper = mapOf(
        Shape.ROCK to mapOf(
            Shape.ROCK to 3,
            Shape.PAPER to 0,
            Shape.SCISSORS to 6,
        ),
        Shape.PAPER to mapOf(
            Shape.ROCK to 6,
            Shape.PAPER to 3,
            Shape.SCISSORS to 0,
        ),
        Shape.SCISSORS to mapOf(
            Shape.ROCK to 0,
            Shape.PAPER to 6,
            Shape.SCISSORS to 3,
        ),
    )

    private val charToShape = mapOf(
        'A' to Shape.ROCK,
        'X' to Shape.ROCK,
        'B' to Shape.PAPER,
        'Y' to Shape.PAPER,
        'C' to Shape.SCISSORS,
        'Z' to Shape.SCISSORS,
    )

    override fun calculateAnswer(input: MutableList<String>): Int {
        val answer = input.map { it.replace(" ", "") }
            .map { Pair(charToShape.getValue(it[1]), charToShape.getValue(it[0])) }
            .sumOf {
                shapeToScoreMapper.getValue(it.first).getValue(it.second) + it.first.score
            }

        println("Answer: $answer")
        return answer;
    }

}