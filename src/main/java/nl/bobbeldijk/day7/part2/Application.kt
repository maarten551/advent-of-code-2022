package nl.bobbeldijk.day7.part2

import nl.bobbeldijk.day7.part1.Directory
import nl.bobbeldijk.day7.part1.calculateSize
import nl.bobbeldijk.day7.part1.getAllDirectories
import nl.bobbeldijk.day7.part1.parseCommandLine
import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY7))
}

private const val DISK_SIZE = 70000000
private const val REQUIRED_UNUSED_SPACE = 30000000

class Application : nl.bobbeldijk.day7.part1.Application(), Answerable<Int> {
    override fun calculateAnswer(input: MutableList<String>): Int {
        val rootDirectory = Directory("/", null)
        var directoryCursor = rootDirectory
        input.removeAt(0) // Ignore the first line

        while (input.size > 0) {
            val line = input.removeAt(0)
            val parsedCommand = parseCommandLine.find(line) ?: throw Error("Failed to parse command $line")

            when (parsedCommand.groupValues[1]) {
                "ls" -> commandListFiles(directoryCursor, input)
                "cd" -> directoryCursor = commandChangeDirectory(directoryCursor, parsedCommand.groupValues.drop(2))
                else -> throw Error("Unimplemented command '${parsedCommand.groupValues[1]}'")
            }
        }

        val neededSpace = REQUIRED_UNUSED_SPACE - (DISK_SIZE - rootDirectory.calculateSize())
        val answer = rootDirectory.getAllDirectories()
                .map { it.calculateSize() }
                .filter { it >= neededSpace }
                .minOf { it }

        println("Answer: $answer")
        return answer
    }
}
