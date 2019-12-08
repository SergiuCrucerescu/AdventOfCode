package day1

import java.io.File

fun main() {
    val result = File("day1.txt").useLines { processInput(it) }
    println(result)
}

fun processInput(lines: Sequence<String>) = lines
    .map(String::toInt)
    .map { it / 3 - 2 }
    .sum()