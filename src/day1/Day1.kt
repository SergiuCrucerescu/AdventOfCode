package day1

import java.io.File

fun main() {
    val result = File("day1.txt").useLines { processInput(it) }
    println(result)
}

fun processInput(lines: Sequence<String>): Int =
    lines.map(String::toInt)
    .map { computeOverallFuel(computeFuel(it)) }
    .sum()

tailrec fun computeOverallFuel(mass: Int): Int = when {
    mass > 0 -> mass + computeOverallFuel(computeFuel(mass))
    else -> 0
}

fun computeFuel(mass: Int) = mass / 3 - 2