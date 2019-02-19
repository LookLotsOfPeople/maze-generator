package com.beyondbell.mazegenerator.maze.rooms

import com.beyondbell.mazegenerator.maze.cells.Cell

class Room(private val width: Int, private val height: Int) {
	fun addRoom(x: Int, y: Int, grid: Array<Array<Cell>>): Array<Array<Cell>> {
		for (cellY in y until y + height) {
			for (cellX in x until x + width) {
				grid[cellY][cellX].bottomWall = false
				grid[cellY][cellX].leftWall = false
				grid[cellY][cellX].rightWall = false
				grid[cellY][cellX].topWall = false
			}
		}
		return grid
	}
}