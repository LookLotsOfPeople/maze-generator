package com.beyondbell.mazegenerator

import com.beyondbell.mazegenerator.menus.MainMenu

fun main() {
	Array(1) { Game() }
}

class Game {
	init {
		MainMenu(this)
	}

	fun startGame() {

	}
}