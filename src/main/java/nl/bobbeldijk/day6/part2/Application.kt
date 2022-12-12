package nl.bobbeldijk.day6.part2

import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY6))
}

private const val PACKAGE_LENGTH = 14

class Application : Answerable<Int> {
    override fun calculateAnswer(input: MutableList<String>): Int {
        val answer = input.flatMap {
            (0 until it.length - PACKAGE_LENGTH - 1)
                .map { index -> Pair(index, it.substring(index, index + PACKAGE_LENGTH)) }
        }
            .find { it.second.sumOf { char -> it.second.count { c2 -> c2 == char } } == PACKAGE_LENGTH }!!.first + PACKAGE_LENGTH


        println("Answer: $answer")
        return answer
    }
}
