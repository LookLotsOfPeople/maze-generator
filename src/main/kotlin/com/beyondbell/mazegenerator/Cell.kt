package com.beyondbell.mazegenerator

open class Cell(val y: Int, val x: Int) {
	var topWall = true
	var bottomWall = true
	var leftWall = true
	var rightWall = true

	fun getReadyNeighbors(grid: Array<Array<Cell>>, ids: Array<Array<Int>>): Array<Cell> {
		val readyNeighbors = ArrayList<Cell>()

		arrayOf(Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1)).forEach {
			val y = y + it.first
			val x = x + it.second
			val cell = grid[y][x]
			if (cell !is Wall && ids[y][x] == -1) {
				readyNeighbors.add(cell)
			}
		}

		return readyNeighbors.toTypedArray()
	}

	fun connect(neighbor: Cell): Cell {
		if (x < neighbor.x) {
			rightWall = false
			neighbor.leftWall = false
		}
		if (x > neighbor.x) {
			leftWall = false
			neighbor.rightWall = false
		}
		if (y < neighbor.y) {
			bottomWall = false
			neighbor.topWall = false
		}
		if (y > neighbor.y) {
			topWall = false
			neighbor.bottomWall = false
		}
		return neighbor
	}
}