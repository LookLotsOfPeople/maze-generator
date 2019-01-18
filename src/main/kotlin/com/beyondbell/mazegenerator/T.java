package com.beyondbell.mazegenerator;

import com.beyondbell.mazegenerator.cells.Cell;
import com.beyondbell.mazegenerator.cells.CellProperty;

import javax.swing.JApplet;
import java.awt.Graphics;

public class T extends JApplet {
	private Cell[][] grid = new Maze(350, 700).generate().get();
	private int size = 2;

	@Override
	public void init() {
		super.init();
		resize(size * grid[0].length + 1, size * grid.length + 1);
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
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
	}
}
