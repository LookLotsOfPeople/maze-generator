package com.beyondbell.mazegenerator.cells

open class Cell(val y: Int, val x: Int, var property: CellProperty = CellProperty.Default) {
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
			if (cell.property != CellProperty.Wall && ids[y][x] == -1) {
				readyNeighbors.add(cell)
			}
		}

		return readyNeighbors.toTypedArray()
	}

	fun connect(neighbor: Cell): Cell {
		when {
			x < neighbor.x -> {
				rightWall = false
				neighbor.leftWall = false
			}
			x > neighbor.x -> {
				leftWall = false
				neighbor.rightWall = false
			}
			y < neighbor.y -> {
				bottomWall = false
				neighbor.topWall = false
			}
			y > neighbor.y -> {
				topWall = false
				neighbor.bottomWall = false
			}
		}
		return neighbor
	}
}