package nl.bobbeldijk.day3.part2

import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY3))
}

class Application : Answerable<Int> {
    override fun calculateAnswer(input: MutableList<String>): Int {
        val answer = input.chunked(3)
            .map { chunks -> chunks[0].first { c -> chunks.drop(1).all { it.contains(c) } } }
            .sumOf { if (it.isUpperCase()) it.code - 38 else it.code - 96 }

        println("Answer: $answer")
        return answer
    }

}