package com.beyondbell.mazegenerator.maze

import com.beyondbell.mazegenerator.maze.cells.Cell
import com.beyondbell.mazegenerator.maze.cells.CellProperty
import com.beyondbell.mazegenerator.maze.rooms.Room
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

		Room(5, 5).addRoom(2, 2, grid)
		Room(5, 5).addRoom(17, 2, grid)
		Room(5, 5).addRoom(19, 26, grid)
	}

	@JvmOverloads
	fun generate(collapses: Int = 0, gaps: Int = 0, rooms: Int = 0, roomsSize: Int = 5, startPosition: Pair<Int, Int> = Pair(Random.nextInt(grid.size - 2), Random.nextInt(grid[0].size - 2))): Maze {
		val cellHistory = ArrayList<Cell>()
		val traversed = Array(grid.size) {Array(grid[0].size) { false } }
		var cell = grid[startPosition.first + 1][startPosition.second + 1]
		traversed[cell.y][cell.x] = true

		var neighbors = cell.getReadyNeighbors(grid, traversed)
		do {
			if (neighbors.isNotEmpty()) {
				cellHistory.add(cell)
				cell = cell.connect(neighbors[Random.nextInt(neighbors.size)])
				traversed[cell.y][cell.x] = true
			} else {
				cell = cellHistory.last()
				cellHistory.removeAt(cellHistory.lastIndex)
			}

			neighbors = cell.getReadyNeighbors(grid, traversed)
		} while (cellHistory.isNotEmpty() || neighbors.isNotEmpty())

		// Random Connections
		for (i in 0 until gaps) {
			val randomCell = grid[Random.nextInt(grid.size - 2)][Random.nextInt(grid[0].size - 2)]
			if (randomCell.property == CellProperty.Wall) {
				continue
			}
			randomCell.getAllNeighbors(grid).forEach {
				randomCell.connect(it)
			}
		}

		// Random Collapses
		for (i in 0 until collapses) {
			val randomCell = grid[Random.nextInt(grid.size - 2) + 1][Random.nextInt(grid[0].size - 2) + 1]
			if (randomCell != CellProperty.Wall) {
				randomCell.disconnect(randomCell.getAllNeighbors(grid).random())
			}
		}

		// Random Rooms
		for (i in 0 until rooms) {
			val randomCell = grid[Random.nextInt(grid.size - 2)][Random.nextInt(grid[0].size - 2)]
			if (randomCell.property != CellProperty.Wall) {
				randomCell.connect(randomCell.getAllNeighbors(grid).random())
			}
		}

		return this
	}

	fun get(): Array<Array<Cell>> {
		return grid.clone()
	}
}