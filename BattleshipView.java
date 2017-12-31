
public class BattleshipView {

	public static void addShipMessage(int index) {
		System.out.println("Where would you like to place the " + Constants.SHIPSIZES[index] + "-cell ship?");
	}

	public static void attackMessage(boolean p1Turn) {
		if (p1Turn) {
			System.out.println("Player 1's turn.");
		} else {
			System.out.println("Player 2's turn.");
		}
		System.out.println("Where would you like to shoot?");
	}

	/**
	 * @brief Prints out the board as seen by the opponent.
	 */
	public static void show(BoardT b) {
		
		System.out.println("   |  A  B  C  D  E  F  G  H  I  J");
		System.out.println("------------------------------------");

		// Print out the rest:
		for (int j = 0; j < Constants.SIZE; j++) {
			System.out.printf("%-2d | ", (j + 1));

			for (int i = 0; i < Constants.SIZE; i++) {
				// If the board hasn't been hit, "~"
				if (!b.getBoardCells()[i][j].isHit()) {
					System.out.printf("%-3s", " ~ ");
				} else if (b.checkShip(i,j)) {
					// If a ship has been sunk, "S"
					if (b.checkSunk(i,j))
						System.out.printf("%-3s", " S ");
					// If a ship has been hit, but not sunk, "X"
					else
						System.out.printf("%-3s", " X ");
					// If a ship has been hit, but missed a ship, <blank>
				} else if (!(b.checkShip(i,j)))
					System.out.printf("%-3s", "  ");
			}
			System.out.println();
		}
		System.out.println();
	}

	// Shows the reality of the situation for the programmer. Can be used
	// instead of show() for testing purposes only.
	public void showRealBoard(BoardT b) {

		CellT[][] board = b.getBoardCells();

		// Print out the X values at the top:
		System.out.println("   |  A    B    C    D    E    F    G    H    I    J");

		System.out.println("-----------------------------------------------------");

		// Print out the rest:
		for (int j = 0; j < Constants.SIZE; j++) {
			System.out.printf("%-2d | ", (j + 1));
			for (int i = 0; i < Constants.SIZE; i++) {
				if (!board[i][j].isHit() && (board[i][j].getShip()==Constants.SIZE))
					System.out.printf("%-5s", "FREE");
				else if (!board[i][j].isHit() && (b.checkShip(i,j)))
					System.out.printf("%-5s", "SHIP");
				else if (b.checkShip(i,j))
					System.out.printf("%-5s", "HIT");
				else 
					System.out.printf("%-5s", "MISS");

			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * This is solely for the purpose of preventing teams from accidentally
	 * revealing private information (i.e. ship locations)
	 * 
	 */
	public static void updateScreen() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}

	/**
	 * Reveals who is winning by printing out a message.
	 * @param p1isWinning True if p1 is winning
	 */
	public static void whoseWinningMessage(boolean p1isWinning) {
		if (p1isWinning)
			System.out.println("Player 1 is in the lead.");
		else
			System.out.println("Player 2 is in the lead.");
	}

	public static void gameOverMessage(boolean p1Turn) {
		if (p1Turn) {
			System.out.println("Congratulations! Player 1 wins.");
		} else {
			System.out.println("Congratulations! Player 2 wins.");
		}
	}
}
