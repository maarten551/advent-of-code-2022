package nl.bobbeldijk.day2.part2

import nl.bobbeldijk.day2.part1.Shape
import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY2))
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

    private val shapeToOutcomeMapper = mapOf(
        Shape.ROCK to mapOf(
            'Z' to Shape.PAPER,
            'Y' to Shape.ROCK,
            'X' to Shape.SCISSORS,
        ),
        Shape.PAPER to mapOf(
            'Z' to Shape.SCISSORS,
            'Y' to Shape.PAPER,
            'X' to Shape.ROCK,
        ),
        Shape.SCISSORS to mapOf(
            'Z' to Shape.ROCK,
            'Y' to Shape.SCISSORS,
            'X' to Shape.PAPER,
        ),
    )

    private val charToShape = mapOf(
        'A' to Shape.ROCK,
        'B' to Shape.PAPER,
        'C' to Shape.SCISSORS,
    )

    override fun calculateAnswer(input: MutableList<String>): Int {
        val answer = input.map { it.replace(" ", "") }
            .map { Pair(charToShape.getValue(it[0]), shapeToOutcomeMapper.getValue(charToShape.getValue(it[0])).getValue(it[1])) }
            .sumOf {
                shapeToScoreMapper.getValue(it.second).getValue(it.first) + it.second.score
            }

        println("Answer: $answer")
        return answer;
    }

}