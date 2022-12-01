package nl.bobbeldijk.day1.part1

import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY1))
}

data class Elf(val inventory: MutableList<Int> = mutableListOf())

class Application : Answerable<Int> {
    override fun calculateAnswer(input: MutableList<String>): Int {
        val elves = mutableListOf(Elf())

        input.forEach {
            if (it.isEmpty()) {
                elves.add(Elf())
                return@forEach
            }

            elves.last().inventory.add(it.toInt())
        }

        val answer = elves.map { it.inventory.sum() }.maxOf { it }

        println("Answer: $answer")
        return answer;
    }

}