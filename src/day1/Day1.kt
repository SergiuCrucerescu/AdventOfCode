package day1

import java.io.File

fun main() {
    val result = File("day1.txt").useLines { processInput(it) }
    println(result)
}

fun processInput(lines: Sequence<String>): Int =
    lines.map(String::toInt)
        .flatMap { mass ->
            generateSequence(mass, { it / 3 - 2 })
                .drop(1)
                .takeWhile { it > 0 }
        }.sum()