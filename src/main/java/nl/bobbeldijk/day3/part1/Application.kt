package nl.bobbeldijk.day3.part1

import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY3))
}

class Application : Answerable<Int> {
    override fun calculateAnswer(input: MutableList<String>): Int {
        val answer = input.map { Pair(it.substring(0, it.length / 2), it.substring(it.length / 2, it.length)) }
            .map { it.first.filter { c -> it.second.contains(c) } }
            .sumOf { if (it[0].isUpperCase()) it[0].code - 38 else it[0].code - 96 }

        println("Answer: $answer")
        return answer
    }

}