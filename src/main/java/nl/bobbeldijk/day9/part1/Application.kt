package nl.bobbeldijk.day9.part1

import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader
import java.util.*
import kotlin.math.abs

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY9))
}

data class Coordinate(val y: Int, val x: Int) {
    operator fun times(by: Int): Coordinate {
        return Coordinate(this.y * by, this.x * by)
    }

    operator fun plus(by: Coordinate): Coordinate {
        return Coordinate(this.y + by.y, this.x + by.x)
    }

    operator fun minus(by: Coordinate): Coordinate {
        return Coordinate(this.y - by.y, this.x - by.x)
    }

    fun stickTo(to: Coordinate): Coordinate {
        val difference = to - this
        val x = difference.x - difference.x.coerceIn(-1..1)
        val y = difference.y - difference.y.coerceIn(-1..1)

        if (abs(x) > 0 && abs(difference.y) > 0) {
            return Coordinate(this.y + (y + difference.y), this.x + x)
        }

        if (abs(y) > 0 && abs(difference.x) > 0) {
            return Coordinate(this.y + y, this.x + (x + difference.x))
        }

        return Coordinate(this.y + y, this.x + x)
    }
}

open class Application : Answerable<Int> {
    override fun calculateAnswer(input: MutableList<String>): Int {
        val visitedCoordinates = mutableSetOf<Coordinate>()
        val rope = LinkedList((0..1).map { Coordinate(0, 0) })
        val directions = mapOf(
            Pair('U', Coordinate(1, 0)),
            Pair('R', Coordinate(0, 1)),
            Pair('D', Coordinate(-1, 0)),
            Pair('L', Coordinate(0, -1)),
        )

        input.flatMap { (0 until it.substring(2).toInt()).map { _ -> directions.getValue(it[0]) } }
            .forEach {
                rope[0] = rope[0] + it

                (1 until rope.size).forEach { i ->
                    rope[i] = rope[i].stickTo(rope[i - 1])
                    visitedCoordinates.add(rope[i])
                }
            }

        val answer = visitedCoordinates.size
        println("Answer: $answer")
        return answer
    }
}
