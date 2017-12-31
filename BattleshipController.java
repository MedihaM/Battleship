
/** @file BattleshipController.java
 *  @title BattleshipController
 *  @author Mediha Munim, munimm
 *  @date 03/26/2017
 */

import java.util.Scanner;

public class BattleshipController { // The controller

	// Runs a game of Battleship:
	public static void main(String[] args) {
		
		setUpBoard();
		while (!Battleship.isGameOver()) {

			if (Battleship.isP1Turn()) {
				BattleshipView.show(Battleship.getP2Board());
				Battleship.getP2Board().attack(getAttackCell());
				BattleshipView.updateScreen();

			} else {
				BattleshipView.show(Battleship.getP1Board());
				Battleship.getP1Board().attack(getAttackCell());
				BattleshipView.updateScreen();
			}

			if (Battleship.getP1Board().remainingShipCells() == 0 || Battleship.getP2Board().remainingShipCells() == 0) {
				BattleshipView.gameOverMessage(Battleship.isP1Turn());
				Battleship.setGameOver();
			}
			Battleship.switchTurn(); // switch players
		}

	}

	/**
	 * 
	 * 
	 */
	public static void setUpBoard() {
		
		// Get the players to add ships:
		System.out.println("Player 1 - Add ships (e.g., type A2 E2) ");
		Battleship.setP1Board(shipSetUp(Battleship.getP1Board()));
		BattleshipView.updateScreen();

		System.out.println("Player 2 - Add ships (e.g., type A2 E2) ");
		Battleship.setP2Board(shipSetUp(Battleship.getP2Board()));
		BattleshipView.updateScreen();

	}

	public static BoardT shipSetUp(BoardT b) {

		Scanner keyboard = new Scanner(System.in);
		for (int i = 0; i < 5; i++) {
			BattleshipView.addShipMessage(i);
			String cell1 = keyboard.next();
			String cell2 = keyboard.next();

			// Convert x letters to numbers/indices
			int c1x = cell1.charAt(0) - 'A';
			// y number on board is not the same as index in board[][] -
			// subtract 1
			int c1y = Integer.parseInt(cell1.substring(1)) - 1;
			// Convert x letters to numbers/indices
			int c2x = cell2.charAt(0) - 'A';
			// y number on board is not the same as index in board[][] -
			// subtract 1
			int c2y = Integer.parseInt(cell2.substring(1)) - 1;

			CellT c1 = new CellT(c1x, c1y);
			CellT c2 = new CellT(c2x, c2y);

			b.addShip(c1, c2, i);
		}
		return b;
	}

	private static CellT getAttackCell() {

		BattleshipView.attackMessage(Battleship.isP1Turn());

		Scanner keyboard = new Scanner(System.in);
		String cell = keyboard.next();

		// Convert x letters to numbers/indices
		int cx = cell.charAt(0) - 'A';
		// y number on board is not the same as index in board[][] -
		// subtract 1
		int cy = Integer.parseInt(cell.substring(1)) - 1;

		CellT c = new CellT(cx, cy);
		return c;
	}



}
