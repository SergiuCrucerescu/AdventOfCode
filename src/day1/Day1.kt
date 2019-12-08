package day1

import java.io.File
import kotlin.test.assertEquals

fun main() {
    val result = File("day1.txt").useLines { processInput(it) }

    assertEquals(2, computeFuelSequence(12).sum())
    assertEquals(2, computeFuelSequence(14).sum())
    assertEquals(966, computeFuelSequence(1969).sum())
    assertEquals(50346, computeFuelSequence(100756).sum())

    println(result)
}

fun processInput(lines: Sequence<String>): Int =
    lines.map(String::toInt)
        .flatMap { computeFuelSequence(it) }
        .sum()

fun computeFuelSequence(mass: Int): Sequence<Int> =
    generateSequence(mass, { it / 3 - 2 })
        .drop(1)
        .takeWhile { it > 0 }