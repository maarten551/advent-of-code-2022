package nl.bobbeldijk.day7.part1

import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY7))
}

data class File (val name: String, val size: Int)

data class Directory (
        val name: String,
        val parent: Directory?,
        val directories: MutableMap<String, Directory> = mutableMapOf(),
        val files: MutableMap<String, File> = mutableMapOf(),
)

fun Directory.calculateSize(): Int {
    return this.files.values.sumOf { it.size } + this.directories.values.sumOf { it.calculateSize() }
}
fun Directory.getAllDirectories(): List<Directory> {
    return this.directories.values + this.directories.values.flatMap { it.getAllDirectories() }
}

val parseCommandLine = Regex("\\$ ([A-z]+) ?([A-z.]+)?", RegexOption.DOT_MATCHES_ALL)
val parseFileLine = Regex("([A-z0-9]+) ([A-z.]+)")

open class Application : Answerable<Int> {
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

        val answer = rootDirectory.getAllDirectories()
                .map { it.calculateSize() }
                .filter { it <= 100000 }
                .sumOf { it }

        println("Answer: $answer")
        return answer
    }

    protected fun commandChangeDirectory(currentDir: Directory, arguments: List<String>): Directory {
        if (arguments[0] == "..") {
            return currentDir.parent!!
        }

        return currentDir.directories[arguments[0]]!!
    }

    protected fun commandListFiles(currentDir: Directory, input: MutableList<String>) {
        while(input.size > 0 && input[0][0] != '$') {
            val itemLine = input.removeAt(0)
            val parsedItem = parseFileLine.find(itemLine) ?: throw Error("Failed to parse file/directory: '$itemLine'")

            when(parsedItem.groupValues[1]) {
                "dir" -> currentDir.directories[parsedItem.groupValues[2]] = Directory(parsedItem.groupValues[2], currentDir)
                else -> currentDir.files[parsedItem.groupValues[2]] = File(parsedItem.groupValues[2], parsedItem.groupValues[1].toInt())
            }
        }
    }
}
