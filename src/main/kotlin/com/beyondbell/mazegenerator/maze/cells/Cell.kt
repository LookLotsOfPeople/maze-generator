package com.beyondbell.mazegenerator.maze.cells

open class Cell(val y: Int, val x: Int, var property: CellProperty = CellProperty.Default) {
	var topWall = true
	var bottomWall = true
	var leftWall = true
	var rightWall = true

	fun getReadyNeighbors(grid: Array<Array<Cell>>, traversed: Array<Array<Boolean>>): Array<Cell> {
		val readyNeighbors = ArrayList<Cell>()

		arrayOf(Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1)).forEach {
			val y = y + it.first
			val x = x + it.second
			val cell = grid[y][x]
			if (cell.property != CellProperty.Wall && !traversed[y][x]) {
				readyNeighbors.add(cell)
			}
		}

		return readyNeighbors.toTypedArray()
	}

	fun getAllNeighbors(grid: Array<Array<Cell>>): Array<Cell> {
		val neighbors = ArrayList<Cell>()

		arrayOf(Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1)).forEach {
			val y = y + it.first
			val x = x + it.second
			val cell = grid[y][x]
			if (cell.property != CellProperty.Wall) {
				neighbors.add(cell)
			}
		}

		return neighbors.toTypedArray()
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

	fun disconnect(neighbor: Cell): Cell {
		when {
			x < neighbor.x -> {
				rightWall = true
				neighbor.leftWall = true
			}
			x > neighbor.x -> {
				leftWall = true
				neighbor.rightWall = true
			}
			y < neighbor.y -> {
				bottomWall = true
				neighbor.topWall = true
			}
			y > neighbor.y -> {
				topWall = true
				neighbor.bottomWall = true
			}
		}
		return neighbor
	}
}