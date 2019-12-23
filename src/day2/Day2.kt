package day2

import java.io.File
import kotlin.test.assertEquals

fun main() {
    assertEquals(listOf(2, 0, 0, 0, 99), computeIntCode(listOf(1, 0, 0, 0, 99)))
    assertEquals(listOf(2, 3, 0, 6, 99), computeIntCode(listOf(2, 3, 0, 3, 99)))
    assertEquals(listOf(2, 4, 4, 5, 99, 9801), computeIntCode(listOf(2, 4, 4, 5, 99, 0)))
    assertEquals(listOf(30, 1, 1, 4, 2, 5, 6, 0, 99), computeIntCode(listOf(1, 1, 1, 4, 99, 5, 6, 0, 99)))

    File("day2.txt").useLines { processInput(it, 12, 2) }.also(::println)

    (0..99).flatMap { noun -> (0..99).map { verb -> Pair(noun, verb) } }
            .find { pair ->
                File("day2.txt").useLines { processInput(it, pair.first, pair.second) } == 19690720
            }
            .also(::println)
            ?.let { 100 * it.first + it.second }
            .also(::println)
}

fun processInput(lines: Sequence<String>, noun: Int, verb: Int): Int =
        lines.first()
                .split(',')
                .map(String::toInt)
                .toMutableList()
                .apply {
                    this[1] = noun
                    this[2] = verb
                }
                .let(::computeIntCode)
                .first()


fun computeIntCode(integers: List<Int>): List<Int> =
        integers.toMutableList().apply {
            generateSequence(0) { it + 4 }
                    .takeWhile { it + 3 < this.size && this[it] != 99 }
                    .forEach { processChunk(this, it) }
        }


fun processChunk(integers: MutableList<Int>, i: Int): Unit {
    integers.apply {
        val operator: (Int, Int) -> Int = when (this[i]) {
            1 -> Int::plus
            2 -> Int::times
            else -> throw IllegalArgumentException("Expected valid opCode")
        }
        this[this[i + 3]] = operator.invoke(this[this[i + 1]], this[this[i + 2]])
    }
}