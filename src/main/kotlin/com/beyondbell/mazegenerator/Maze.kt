package com.beyondbell.mazegenerator

import com.beyondbell.mazegenerator.cells.Cell
import com.beyondbell.mazegenerator.cells.CellProperty
import kotlin.random.Random

class Maze {
	private val grid: Array<Array<Cell>>

	constructor(dimension: Int) {
		grid = Array(dimension + 2) { y -> Array(dimension + 2) { x -> Cell(y, x) } }
		for (i in 0 until grid.size) {
			grid[i][0] = Cell(i, 0, CellProperty.Wall)
			grid[i][grid[0].size - 1] = Cell(i, grid[0].size - 1, CellProperty.Wall)
			grid[0][i] = Cell(0, i, CellProperty.Wall)
			grid[grid.size - 1][i] = Cell(grid.size - 1, i, CellProperty.Wall)
		}
	}

	constructor(dimensionY: Int, dimensionX: Int) {
		grid = Array(dimensionY + 2) { y -> Array(dimensionX + 2) { x -> Cell(y, x) } }
		for (i in 0 until grid.size) {
			grid[i][0] = Cell(i, 0, CellProperty.Wall)
			grid[i][grid[0].size - 1] = Cell(i, grid[0].size - 1, CellProperty.Wall)
		}
		for (i in 0 until grid[0].size) {
			grid[0][i] = Cell(0, i, CellProperty.Wall)
			grid[grid.size - 1][i] = Cell(grid.size - 1, i, CellProperty.Wall)
		}
	}

	@JvmOverloads
	fun generate(startPosition: Pair<Int, Int> = Pair(Random.nextInt(grid.size - 2), Random.nextInt(grid[0].size - 2))): Maze {
		val cellHistory = ArrayList<Cell>()
		val ids = Array(grid.size) {Array(grid[0].size) { -1 } }
		var cell = grid[startPosition.first + 1][startPosition.second + 1]
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
		} while (cellHistory.isNotEmpty() || neighbors.isNotEmpty())

		return this
	}

	fun get(): Array<Array<Cell>> {
		return grid.clone()
	}
}

fun main(args: Array<String>) {
	while (true) {
		val maze = Maze(100)
		println(maze)
	}
}