package com.beyondbell.mazegenerator

import kotlin.random.Random

class Maze {
	private val grid: Array<Array<Cell>>

	constructor(dimension: Int) {
		grid = Array(dimension + 2) { y -> Array(dimension + 2) { x -> Cell(y, x) } }
		for (i in 0 until grid.size) {
			grid[i][0] = Wall(i, 0)
			grid[i][grid.size - 1] = Wall(i, grid.size - 1)
			grid[0][i] = Wall(0, i)
			grid[grid[0].size - 1][i] = Wall(grid[0].size - 1, i)
		}
	}

	constructor(dimensionY: Int, dimensionX: Int) {
		grid = Array(dimensionY + 2) { y -> Array(dimensionX + 2) { x -> Cell(y, x) } }
		for (i in 0 until grid.size) {
			grid[i][0] = Wall(i, 0)
			grid[i][grid.size - 1] = Wall(i, grid.size - 1)
			grid[0][i] = Wall(0, i)
			grid[grid[0].size - 1][i] = Wall(grid[0].size - 1, i)
		}
	}

	fun generate(startPosition: Pair<Int, Int> = Pair(0, 0)): Maze {
		val cellHistory = ArrayList<Cell>()
		val ids = Array(grid.size) {Array(grid[0].size) { -1 } }
		var cell = grid[startPosition.second + 1][startPosition.first + 1]
		ids[cell.y][cell.x] = 0

		var neighbors = cell.getReadyNeighbors(grid, ids)
		do {
			if (neighbors.isNotEmpty()) {
				cellHistory.add(cell)
				cell = cell.connect(neighbors[Random.nextInt(neighbors.size)])
				ids[cell.y][cell.x] = ids[cellHistory.last().y][cellHistory.last().x] + 1
			} else {
				cell = cellHistory.last()
				cellHistory.removeAt(cellHistory.lastIndex)
			}

			neighbors = cell.getReadyNeighbors(grid, ids)
		} while (ids[cell.y][cell.x] != 0 || neighbors.isNotEmpty())

		ids.forEach {
			it.forEach {
				print(it)
				print("\t")
			}
			println()
		}
		println(ids)

		return this
	}

	fun get(): Array<Array<Cell>> {
		return grid.clone()
	}
}