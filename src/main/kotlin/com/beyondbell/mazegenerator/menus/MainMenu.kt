package com.beyondbell.mazegenerator.menus

import com.beyondbell.mazegenerator.Game
import java.awt.Font
import java.awt.event.ActionEvent
import javax.swing.JLabel
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import javax.swing.SwingConstants.CENTER
import javax.swing.WindowConstants
import kotlin.system.exitProcess

class MainMenu(game: Game) : Menu("Main Menu") {
	init {
		jMenuBar = MainMenuBar(this, game)

		val title = JLabel("Mazes # Alpha")
		title.setBounds(0, 0, 800, 600)
		title.horizontalTextPosition = CENTER
		title.horizontalAlignment = CENTER
		title.verticalTextPosition = CENTER
		title.verticalAlignment = CENTER
		title.font = Font("", Font.BOLD, 96)
		add(title)

		setSize(800, 600)
		layout = null

		defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

		isVisible = true
	}

	private class MainMenuBar(mainMenu: MainMenu, game: Game) : JMenuBar() {
		private val buttons = HashMap<String, JMenuItem>()
		private val menus = HashMap<String, JMenu>()

		init {
			addButton("Game", "Exit") { exitProcess(0) }
			addButton("Single Player", "Start Game") { mainMenu.dispose(); game.startGame() }
			addButton("Single Player", "Load Game")
			addButton("Multi Player", "Join Game")
			addButton("Multi Player", "Host Game")

			isVisible = true
		}

		fun addButton(menuName: String, buttonName: String, action: (ActionEvent) -> Unit = {}) {
			val button = JMenuItem(buttonName)
			button.addActionListener {
				action(it)
			}

			buttons[buttonName] = button
			menus.getOrPut(menuName) { add(JMenu(menuName)) }.add(button)
		}
	}
}