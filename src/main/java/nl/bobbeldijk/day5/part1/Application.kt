package nl.bobbeldijk.day5.part1

import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY5))
}

data class Command(val from: Int, val to: Int, val amount: Int)

class Application : Answerable<String> {
    override fun calculateAnswer(input: MutableList<String>): String {
        val initState = input.takeWhile(String::isNotEmpty)
        val commands = input.subList(initState.size + 1, input.size)
        val stacks = (0 until initState.maxOf { it.length }).map { ArrayDeque<Char>(0) }

        // Filling the stacks/ArrayDeque with the init setup
        initState.forEach {
            it.mapIndexed { index, char -> Pair(index, char) }
                .filter { pair -> pair.second.isLetter() }
                .forEach { pair -> stacks[pair.first].addFirst(pair.second) }
        }

        // Execute commands
        commands.map { Regex("\\d+").findAll(it).map { match -> match.value.toInt() }.toList() }
            .map { Command(it[1] - 1, it[2] - 1, it[0]) }
            .forEach { command ->
                repeat(command.amount) { stacks[command.to].addLast(stacks[command.from].removeLast()) }
                println(stacks)
            }

        val answer = stacks.joinToString("", transform = { it.last().toString() })
        println("Answer: $answer")

        return answer
    }

}