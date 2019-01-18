package com.beyondbell.mazegenerator

import java.applet.Applet
import java.awt.Graphics

private val grid: Array<Array<Cell>> = Maze(50).generate().get()

class Test : Applet() {
	override fun init() {
		super.init()
		resize(3 * grid[0].size, 3 * grid.size)
	}

	override fun paint(g: Graphics) {
		super.paint(g)
		grid.forEach {
			it.forEach {
				if (it.topWall) {
					g.drawLine(it.x * 3, it.y * 3, it.x * 3 + 3, it.y * 3)
				}
				if (it.leftWall) {

				}
				if (it.bottomWall) {

				}
				if (it.rightWall) {

				}
			}
		}
	}
}