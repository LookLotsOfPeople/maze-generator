package com.beyondbell.mazegenerator

import com.beyondbell.mazegenerator.menus.MainMenu

class Game {
	init {
		MainMenu(this)
	}

	fun startGame() {

	}

	companion object {
		private const val gameCount = 2

		@JvmStatic
		fun main(args: Array<String>) {
			Array(gameCount) { Game() }
		}
	}
}