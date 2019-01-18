package com.beyondbell.mazegenerator;

import kotlin.Pair;

import java.applet.Applet;
import java.awt.Graphics;

public class T extends Applet {
	private Cell[][] grid = new Maze(50).generate(new Pair<>(0, 0)).get();
	private int size = 10;

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
				if (cell instanceof Wall) {
					graphics.fillRect(cell.getX() * size, cell.getY() * size, cell.getX() * size + size, cell.getY() * size + size);
				} else {
					if (cell.getTopWall()) {
						graphics.drawLine(cell.getX() * size, cell.getY() * size, cell.getX() * size + size, cell.getY() * size);
					}
					if (cell.getLeftWall()) {
						graphics.drawLine(cell.getX() * size, cell.getY() * size, cell.getX() * size, cell.getY() * size + size);
					}
					if (cell.getBottomWall()) {
						graphics.drawLine(cell.getX() * size + size, cell.getY() * size, cell.getX() * size + size, cell.getY() * size + size);
					}
					if (cell.getRightWall()) {
						graphics.drawLine(cell.getX() * size + size, cell.getY() * size, cell.getX() * size + size, cell.getY() * size + size);
					}
				}
			}
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		grid = new Maze(50).generate(new Pair<>(0, 0)).get();
		repaint();
	}
}
