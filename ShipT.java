/** @file ShipT.java
 *  @title ShipT
 *  @author Mediha Munim, munimm
 *  @date 03/26/2017
 */

/**
 * @brief An abstract data type that represents a ship.
 * @details Represents a ship, which is a sequence of adjacent cells. Defined by
 *          its size, its start cell, and its end cell.
 */
public class ShipT {

	// The sequence of cells that make up the ship.
	private CellT[] shipCells;

	// True if the ship is situated vertically on the board, false if the ship
	// is horizontal
	private boolean vertical;

	// The length of the ship
	private int length;

	// True if the ship has sunk
	private boolean sunk = false;

	/**
	 * @brief Constructs the ship.
	 * @details Creates a ship from one cell to another cell, accounting for the
	 *          cells inbetween them.
	 * @param shipSize
	 * @param board
	 *            The board on which to add the ship
	 * @param x
	 *            The alphabetical value of the x-coordinate
	 * @param y
	 *            The numerical value of the y-coordinate
	 * @throw InvalidShipException if the ship does not have the expected
	 *        length.
	 */
	public ShipT(int shipSize, CellT c1, CellT c2) {

		// Ensure that the ship is either horizontal or vertical
		checkVertical(c1, c2);

		// Ensure that the ship is the correct length
		if (vertical) {
			length = Math.abs(c1.getY() - c2.getY()) + 1;
		} else {
			length = Math.abs(c1.getX() - c2.getX()) + 1;
		}

		if (length != shipSize) {
			System.out.println(length);
			throw new InvalidShipException("Error - Ship has the wrong size.");
		}

		// Make the array of cells that make up the ship
		shipCells = new CellT[shipSize];

		// Finally, put the cells that the ship covers in array shipCells:
		shipCells = findShipCells(c1, c2);
	}


	/**
	 * @brief Checks if the ship is vertical.
	 * @return true if the ship is vertical, false if it is horizontal.
	 */
	public boolean isVertical() {
		return vertical;
	}

	/**
	 * @brief Returns the cells that make up the ship.
	 * @return the array of cells that make up the ship.
	 */
	public CellT[] getCells() {
		return shipCells;
	}

	/**
	 * @brief Sets the ship's status to 'sunk'.
	 */
	public void sink() {
		if (sunk){
			throw new InvalidMoveException("Error - Ship has already been sunk.");
		}
		sunk = true;
	}

	/**
	 * @brief Checks if the ship has sunk.
	 * @return true if the ship has sunk.
	 */
	public boolean isSunk() {
		return sunk;
	}

	/**
	 * @brief Checks if the ship occupies a cell on the board.
	 * @param c
	 *            The cell that is being checked.
	 * @return true if the cell is occupied by the ship.
	 */
	public boolean hasCell(CellT c) {
		for (CellT shipCells : getCells()) {
			if (shipCells.sameCell(c)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @brief Checks the ships orientation.
	 * @param c1
	 *            The first cell of the ship.
	 * @param c2
	 *            The second cell of the ship.
	 * @return true if the ship is oriented vertically, false if otherwise.
	 * @throw InvalidShipException if the ship is not oriented perpendicularly.
	 */
	private void checkVertical(CellT c1, CellT c2) {
		if (c1.getX() == c2.getX()) {
			vertical = true;
		} else if (c1.getY() == c2.getY()) {
			vertical = false;
		} else {
			throw new InvalidShipException("Error - Ship must be horizontal or vertical.");
		}
	}
	
	private CellT[] findShipCells(CellT c1, CellT c2){
		
		CellT[] tempShipCells = new CellT[length];
		if (isVertical()) {
			if (c1.getY() < c2.getY()) {
				for (int i = 0; i < length; i++) {
					tempShipCells[i] = new CellT(c1.getX(), c1.getY() + i);
				}
			} else if (c1.getY() > c2.getY()) {
				for (int i = 0; i < length; i++) {
					tempShipCells[i] = new CellT(c1.getX(), c2.getY() + i);
				}
			}
		} else { // If the ship is horizontal
			if (c1.getX() < c2.getX()) {
				for (int i = 0; i < length; i++) {
					tempShipCells[i] = new CellT(c1.getX() + i, c1.getY());
				}
			} else if (c1.getX() > c2.getX()) {
				for (int i = 0; i < length; i++) {
					tempShipCells[i] = new CellT(c2.getX() + i, c2.getY());
				}
			}
		}
		
		return tempShipCells;
		
	}

}
