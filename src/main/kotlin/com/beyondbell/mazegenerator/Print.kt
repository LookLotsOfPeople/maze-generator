package com.beyondbell.mazegenerator

import com.beyondbell.mazegenerator.maze.Maze
import com.beyondbell.mazegenerator.maze.cells.CellProperty

fun main() {
	val s = StringBuilder()

	val gridPieces = arrayListOf("/=|",
			"/==", "==|", "/_|", "/^|",
			"===", "/ |",
			"/  ", "  |", "___", "^^^",
			"   ", "***")

	Maze(35, 70).generate(100, 100).get().forEach {
		it.forEach {
			val choices = gridPieces.toMutableList()
			if (it.property == CellProperty.Wall) {
				s.append("XXX")
			} else {
				val indexes = ArrayList<Int>()
				if (!it.topWall) {
					choices.forEachIndexed { index, choice ->
						if (choice.contains("^") || choice.contains("=")) {
							if (!indexes.contains(index)) {
								indexes.add(index)
							}
						}
					}
				}
				if (!it.leftWall) {
					choices.forEachIndexed { index, choice ->
						if (choice.contains("/")) {
							if (!indexes.contains(index)) {
								indexes.add(index)
							}
						}
					}
				}
				if (!it.bottomWall) {
					choices.forEachIndexed { index, choice ->
						if (choice.contains("_") || choice.contains("=")) {
							if (!indexes.contains(index)) {
								indexes.add(index)
							}
						}
					}
				}
				if (!it.rightWall) {
					choices.forEachIndexed { index, choice ->
						if (choice.contains("|")) {
							if (!indexes.contains(index)) {
								indexes.add(index)
							}
						}
					}
				}
				indexes.sortDescending()
				indexes.forEach {
					println(it)
					choices.removeAt(it)
				}
				s.append(choices.first())
			}
		}
		s.appendln()
	}

	println(s.toString())
}