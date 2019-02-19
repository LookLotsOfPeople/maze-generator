package com.beyondbell.mazegenerator.maze;

import com.beyondbell.mazegenerator.maze.cells.Cell;
import com.beyondbell.mazegenerator.maze.cells.CellProperty;

import java.applet.Applet;
import java.awt.Graphics;

public class T extends Applet {
	private Cell[][] grid = new Maze(35, 70).generate(100, 100).get();
	private int size = 20;

	private boolean alt = false;

	@Override
	public void init() {
		super.init();
		resize(size * grid[0].length, size * grid.length);
	}

	@Override
	public void paint(Graphics graphics) {
		for (Cell[] cells : grid) {
			for (Cell cell : cells) {
				if (cell.getProperty() == CellProperty.Wall) {
					graphics.fillRect(cell.getX() * size, cell.getY() * size, cell.getX() * size + size, cell.getY() * size + size);
				} else {
					if (cell.getTopWall()) {
						graphics.drawLine(cell.getX() * size, cell.getY() * size, cell.getX() * size + size, cell.getY() * size);
					}
					if (cell.getLeftWall()) {
						graphics.drawLine(cell.getX() * size, cell.getY() * size, cell.getX() * size, cell.getY() * size + size);
					}
					if (cell.getBottomWall()) {
						graphics.drawLine(cell.getX() * size, cell.getY() * size + size, cell.getX() * size + size, cell.getY() * size + size);
					}
					if (cell.getRightWall()) {
						graphics.drawLine(cell.getX() * size + size, cell.getY() * size, cell.getX() * size + size, cell.getY() * size + size);
					}
				}
			}
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		grid = new Maze(35, 70).generate(100, 100).get();
		repaint();
	}
}
