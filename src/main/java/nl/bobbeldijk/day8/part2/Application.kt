package nl.bobbeldijk.day8.part2

import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY8))
}

data class Coordinate(val y: Int, val x: Int) {
    operator fun times(by: Int): Coordinate {
        return Coordinate(this.y * by, this.x * by)
    }

    operator fun plus(by: Coordinate): Coordinate {
        return Coordinate(this.y + by.y, this.x + by.x)
    }
}

data class Tree(
    val pos: Coordinate,
    val height: Int,
    val parent: TreeArea
) {
    fun calculateScenicScore(): Int {
        val directions = arrayOf(Coordinate(-1, 0), Coordinate(0, -1), Coordinate(1, 0), Coordinate(0, 1))

        return directions.map { dir ->
            val treesInDirection = generateSequence(1) { it + 1 }
                .map { dir * it }
                .map { this.pos + it }
                .map { this.parent.getTreeByCoordinate(it) }
                .takeWhile { it != null }
                .filterNotNull()

            var score = 0
            for (tree in treesInDirection) {
                score++

                if (tree.height >= this.height) {
                    break
                }
            }

            score
        }.reduce { acc, i -> acc * i }
    }
}

data class TreeArea(var trees: List<List<Tree>>?) {
    override fun toString(): String = ""

    fun getTreeByCoordinate(pos: Coordinate): Tree? {
        return this.trees!!.getOrNull(pos.y)?.getOrNull(pos.x)
    }
}

open class Application : Answerable<Int> {
    override fun calculateAnswer(input: MutableList<String>): Int {
        val treeArea = TreeArea(null);
        treeArea.trees = input.mapIndexed { y, row -> row.mapIndexed { x, height -> Tree(Coordinate(y, x), height.digitToInt(), treeArea) } }

        val answer = treeArea.trees!!.flatten()
            .maxOf { it.calculateScenicScore() }

        println("Answer: $answer")
        return answer
    }
}
