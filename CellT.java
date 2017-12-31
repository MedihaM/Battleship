/** @file CellT.java
 *  @title CellT
 *  @author Mediha Munim, munimm
 *  @date 03/26/2017
 */

/**
 * @brief An abstract data type that represents a single cell on a board.
 * @details A cell that is defined by it's x and y position on a board.
 * @author Mediha
 *
 */
public class CellT {

	// The x-coordinate of the cell
	private int xpos;

	// The y-coordinate of the cell
	private int ypos;

	// True if the cell has been hit/shot at
	private boolean hit;
	
	// Equals SIZE if the cell is occupied by a ship
	private int ship;

	/**
	 * @brief The constructor of the cell. Creates a cell at location (x,y) on a
	 *        board.
	 * @param x
	 *            The x coordinate of the cell
	 * @param y
	 *            The y coordinate of the cell
	 * @throw InvalidCellException if the cell is out of bounds.
	 */
	public CellT(int x, int y) {

		this.xpos = x;
		this.ypos = y;
		hit = false;
		ship = Constants.SIZE;

		// Throw invalidCellException
		if (x < 0 || x >= Constants.SIZE || y < 0 || y >= Constants.SIZE) {
			throw new InvalidCellException("Error - Cell is out of bounds.");
		}
	}

	/**
	 * @brief Sets the status of the cell to "hit."
	 */
	public void setHit() {
		hit = true;
	}

	/**
	 * @brief Sets the status of the cell to being occupied by a ship.
	 */
	public void setShip(int shipIndex) {
		ship = shipIndex;
	}

	/**
	 * @brief Checks to see if a cell has already been hit.
	 * @return true if the cell has already been hit.
	 */
	public boolean isHit() {
		return hit;
	}

	/**
	 * @details Checks if the cell is occupied by a ship.
	 * @return true if the cell is occupied by a ship.
	 */
	public int getShip(){
		return ship;
	}

	/**
	 * @brief Returns the x position of the cell on the board.
	 * @return the x position of the cell.
	 */
	public int getX() {
		return xpos;
	}

	/**
	 * @brief Returns the y position of the cell on the board.
	 * @return the y position of the cell.
	 */
	public int getY() {
		return ypos;
	}

	/**
	 * @brief Checks to see if the cell is the same as another.
	 * @details A cell is considered the same as another cell if it occupies the
	 *          same x and y position.
	 * @param c The other cell.
	 * @return true if the cells have the same x and y positions.
	 */
	public boolean sameCell(CellT c) {
		if (this.getX() == c.getX() && this.getY() == c.getY()) {
			return true;
		} else {
			return false;
		}
	}

}
