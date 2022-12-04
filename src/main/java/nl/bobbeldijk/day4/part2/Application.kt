package nl.bobbeldijk.day4.part2

import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY4))
}

class Application : Answerable<Int> {
    override fun calculateAnswer(input: MutableList<String>): Int {
        val answer = input.map {// 2-4,6-8
            it.split(",") // [2-4, 6-8]
                .map { range -> range.split("-") } // [[2, 4], [6, 8]]
                .map { numberList -> numberList.map { t -> t.toInt() } } // [[2, 4], [6, 8]]
                .map { range -> range[0]..range[1] } // [2 .. 4, 6 .. 8]
        }
            .map { it.sortedBy { range -> range.count() } }
            .count { it[0].any { n -> it[1].contains(n) } }

        println("Answer: $answer")
        return answer
    }

}