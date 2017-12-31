/** @file Board.java
 *  @title Board
 *  @author Mediha Munim, munimm
 *  @date 03/26/2017
 */

/**
 * @brief A 2-dimensional abstract data type which represents a square board.
 * @details Every cell on the board is a sequence of a cellT objects.
 */
public class BoardT {

	// The sequence of cells that makes up the board
	private CellT[][] board;

	// Holds info on which ships have been sunk
	private boolean[] sunkenShips = new boolean[Constants.SHIPSIZES.length];

	// The number of cells that are ships on the board that have not been hit
	private int remainingShipCells;

	// Tracks the shots made
	private String shotsHistory = "";

	/**
	 * @brief Initializes the board.
	 */
	public BoardT() {
		board = new CellT[Constants.SIZE][Constants.SIZE];

		// Set all of the ships to "not sunk"
		for (int i = 0; i < Constants.SHIPSIZES.length; i++) {
			sunkenShips[i] = false;
		}

		for (int i = 0; i < Constants.SIZE; i++) {
			for (int j = 0; j < Constants.SIZE; j++) {
				board[i][j] = new CellT(i, j);
			}
		}
		remainingShipCells = 0;
	}

	/**
	 * @brief Adds a ship to the board by connecting cells.
	 * @details Creates a ship object, then copies the cells from the ship to
	 *          the board's cells.
	 * @param c1
	 *            The first cell of the ship.
	 * @param c2
	 *            The last cell of the ship.
	 * @param shipIndex
	 *            The number to reference the ship.
	 * @throw InvalidShipException if a ship overlaps with another ship.
	 */
	public void addShip(CellT c1, CellT c2, int shipIndex) {

		int length = getLength(c1, c2);

		if (Constants.SHIPSIZES[shipIndex] != length) {
			throw new InvalidShipException("Error - Ship has the wrong size.");
		}

		if (checkVertical(c1, c2)) {
			if (c1.getY() < c2.getY())
				for (int y = c1.getY(); y <= c2.getY(); y++) {

					if (checkShip(c1.getX(), y)) {
						throw new InvalidShipException("Error - Ship overlaps with another ship.");
					}
					board[c1.getX()][y].setShip(shipIndex);
				}
			else {
				for (int y = c2.getY(); y <= c1.getY(); y++) {

					if (checkShip(c1.getX(), y)) {
						throw new InvalidShipException("Error - Ship overlaps with another ship.");
					}
					board[c1.getX()][y].setShip(shipIndex);
				}
			}
		} else {
			if (c1.getX() < c2.getX())
				for (int x = c1.getX(); x <= c2.getX(); x++) {

					if (checkShip(x, c1.getY())) {
						throw new InvalidShipException("Error - Ship overlaps with another ship.");
					}
					board[x][c1.getY()].setShip(shipIndex);
				}
			else {
				for (int x = c2.getX(); x <= c1.getX(); x++) {

					if (checkShip(x, c1.getY())) {
						throw new InvalidShipException("Error - Ship overlaps with another ship.");
					}
					board[x][c1.getY()].setShip(shipIndex);
				}
			}
		}

		remainingShipCells += Constants.SHIPSIZES[shipIndex];
	}

	/**
	 * @brief Checks if the cell at a given (x,y) position is occupied by a
	 *        ship.
	 * @param x
	 *            The x position to be checked.
	 * @param y
	 *            The y position to be checked.
	 * @return true if the cell is occupied by a ship.
	 */
	public boolean checkShip(int x, int y) {
		return board[x][y].getShip() < Constants.SIZE;
	}

	/**
	 * @brief Checks if the cell at the given (x,y) position has already been
	 *        hit.
	 * @param x
	 *            The x position to be checked.
	 * @param y
	 *            The y position to be checked.
	 * @return true if the cell has been hit.
	 */
	public boolean checkSunk(int x, int y) {
		int shipIndex = board[x][y].getShip();
		return (sunkenShips[shipIndex]);

	}

	/**
	 * @brief Causes the cell on the board to be hit.
	 * @param cell
	 *            The cell that is hit.
	 * @throw InvalidMoveException if the cell has already been hit.
	 */
	public void attack(CellT cell) {

		if (board[cell.getX()][cell.getY()].isHit()) {
			throw new InvalidMoveException("Error - the cell has already been hit.");
		}

		// Hit the cell if it hasn't been hit:
		board[cell.getX()][cell.getY()].setHit();
		shotsHistory = shotsHistory + String.format("%d, %d %n", cell.getX(), cell.getY());

		boolean allHit = true;

		// If it's a ship, count a hit and see if it sunk
		if (checkShip(cell.getX(), cell.getY())) {
			remainingShipCells--; // reduce the number of unhit ships
			int shipIndex = board[cell.getX()][cell.getY()].getShip();

			for (int i = 0; i < Constants.SIZE; i++) {
				for (int j = 0; j < Constants.SIZE; j++) {
					if ((board[i][j].getShip() == shipIndex) && !(board[i][j].isHit())) {
						allHit = false;
					}
				}
			}

			if (allHit) {
				sunkenShips[shipIndex] = true;
			}
		}

	}

	/**
	 * @brief Returns the number of cells on the board that are occupied by
	 *        ships and have not been hit.
	 * @return The number of ship cells that have not been hit.
	 */
	public int remainingShipCells() {
		return remainingShipCells;
	}

	/**
	 * @brief Returns the board's sequence of cells.
	 * @return The 2D array of cells that make up the board.
	 */
	public CellT[][] getBoardCells() {
		return board;
	}

	/**
	 * @brief Returns the percentage of sunk ships.
	 * @return The number of sinks that have been sunk.
	 */
	public double getShipsSunk() {
		double count = 0;
		for (boolean x : sunkenShips) {
			if (x)
				count++;
		}
		return (count / Constants.SHIPSIZES.length);

	}

	/**
	 * Reveals a lit of shots that were made against the board.
	 * 
	 * @return A list of shots that were made against the board.
	 */
	public String getShotsMade() {
		return shotsHistory;
	}

	/**
	 * @brief Checks the ships orientation.
	 * @param c1
	 *            The first cell of the ship.
	 * @param c2
	 *            The last cell of the ship.
	 * @return true if the ship is oriented vertically, false if otherwise.
	 * @throw InvalidShipException if the ship is not oriented perpendicularly.
	 */
	private boolean checkVertical(CellT c1, CellT c2) {
		if (c1.getX() == c2.getX()) {
			return true;
		} else if (c1.getY() == c2.getY()) {
			return false;
		} else {
			throw new InvalidShipException("Error - Ship must be horizontal or vertical.");
		}
	}

	/**
	 * 
	 * @brief Ensures that the entered ship is of the correct length.
	 * @param c1
	 *            The first cell of the ship.
	 * @param c2
	 *            The last cell of the ship.
	 * @param shipIndex
	 *            The index of the ship.
	 */
	private int getLength(CellT c1, CellT c2) {
		int length;
		if (checkVertical(c1, c2)) {
			length = Math.abs(c1.getY() - c2.getY()) + 1;
		} else {
			length = Math.abs(c1.getX() - c2.getX()) + 1;
		}

		return length;
	}

}
