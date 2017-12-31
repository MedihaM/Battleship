/**
 * @file Battleship.java
 * @title Battleship
 * @author Mediha Munim, munimm
 * @date 04/05/2017
 */

/**
 * The main model class of the game. Holds the game state information.
 * 
 * @author Mediha
 */
class Battleship {

	// First, make the two boards
	static BoardT p1Board = new BoardT(); // player 1
	static BoardT p2Board = new BoardT(); // player 2
	static boolean p1Turn = true; // player 1's turn
	static boolean gameIsOver = false;

	
	/**
	 * @brief Sets player 1's board to the given board.
	 * @details Used to update the board information.
	 * @param b
	 *            The new state of player 1's board.
	 */
	public static void setP1Board(BoardT b) {
		p1Board = b;
	}

	/**
	 * @brief Sets player 2's board to the given board.
	 * @details Used to update the board information.
	 * @param p1Board
	 *            The new state of player 2's board.
	 */
	public static void setP2Board(BoardT b) {
		p2Board = b;
	}

	/**
	 * @brief Returns the player 1 board.
	 * @return player 1's board
	 */
	public static BoardT getP1Board() {
		return p1Board;
	}

	/**
	 * @brief Returns the player 2 board.
	 * @return player 2's board
	 */
	public static BoardT getP2Board() {
		return p2Board;
	}

	/**
	 * @brief Determines whose turn it is.
	 * @return True if it's player 1's turn, false if it's player 2's turn.
	 */
	public static boolean isP1Turn() {
		return p1Turn;
	}
	

	/**
	 * @brief Switches the turn between the players.
	 */
	public static void switchTurn() {
		p1Turn = !p1Turn;
	}

	/**
	 * @brief Checks if the game is over.
	 * @return true if the game is over, false if it's still going.
	 */
	public static boolean isGameOver() {
		return gameIsOver;
	}

	/**
	 * @brief Sets the game status to "game over."
	 */
	public static void setGameOver() {
		gameIsOver = true;
	}

	/**
	 * @brief Checks who is winning.
	 * @return true if player 1 is winning or there is a tie, false otherwise.
	 */
	public static boolean p1IsWinning() {
		if (p1Board.remainingShipCells() >= p2Board.remainingShipCells())
			return true;
		return false;
	}

}