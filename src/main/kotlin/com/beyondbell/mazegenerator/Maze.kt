package com.beyondbell.mazegenerator

class Maze(dimension: Int, startPosition: Pair<Int, Int> = Pair(0, 0), endingPosition: Pair<Int, Int> = Pair(dimension - 1, dimension - 1)) {
	private val grid = Array(dimension + 2) { Array(dimension + 2) { false } }

	init {
		generate()
	}

	private fun generate() {
		
	}
}