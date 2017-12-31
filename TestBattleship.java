import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBattleship {

	CellT c1;
	CellT c2;
	BoardT b;

	@Before
	public void setUp() throws Exception {
		c1 = new CellT(0, 9);
		c2 = new CellT(4, 9);
		b = new BoardT();
	}

	@After
	public void tearDown() throws Exception {
		c1 = null;
	}

	// First perform tests to ensure that CellT works properly:

	@Test
	public void testCellT_Constructor_Location() {
		CellT cell = new CellT(1, 2);
		assertTrue(cell.getX() == 1 && cell.getY() == 2);
	}

	@Test
	public void testCellT_Constructor_Details() {
		CellT cell = new CellT(1, 2);
		assertTrue(!cell.isHit() && (cell.getShip() == Constants.SIZE));
	}

	@Test
	public void testCellT_getX() {
		assertTrue(c1.getX() == 0);
	}

	@Test
	public void testCellT_getY() {
		assertTrue(c1.getY() == 9);
	}

	@Test(expected = InvalidCellException.class)
	public void testLargeInvalidCellException() {
		new CellT(1, 10);
	}

	@Test(expected = InvalidCellException.class)
	public void testNegativeInvalidCellException() {
		new CellT(-1, 5);
	}

	@Test
	public void test_CellT_getShip() {
		assertTrue(c1.getShip() == Constants.SIZE);
	}

	@Test
	public void test_CellT_setShip() {
		c1.setShip(0);
		assertTrue(c1.getShip() == 0);
	}

	@Test
	public void test_CellT_isHit() {
		assertTrue(!c1.isHit());
	}

	@Test
	public void test_CellT_setHit() {
		c1.setHit();
		assertTrue(c1.isHit());
	}

	@Test
	public void test_CellT_SameCell() {
		CellT cell = new CellT(0, 9);
		cell.setHit();
		assertTrue(c1.sameCell(cell));
	}

	@Test
	public void test_CellT_NotSameCell() {
		CellT cell = new CellT(1, 1);
		assertTrue(!c1.sameCell(cell));
	}

	// Next perform tests to ensure that BoardT works properly:

	@Test
	public void testBoardT_Constructor() {
		BoardT board = new BoardT();
		CellT[][] boardCells = board.getBoardCells();

		assertTrue(boardCells[0][0].getX() == 0);
		assertTrue(boardCells[0][0].getY() == 0);

		assertTrue(boardCells[9][9].getX() == 9);
		assertTrue(boardCells[9][9].getY() == 9);
	}

	@Test
	public void testBaurdT_addShip_VerticalEdge() {
		b.addShip(c1, c2, 0);
		assertTrue(b.checkShip(0, 9));
	}

	@Test
	public void testBoardT_addShip_VerticalBetweenPoint() {
		b.addShip(c2, c1, 0);
		assertTrue(b.checkShip(2, 9));
	}

	@Test
	public void testBoardT_addShip_HorizontalForwards() {
		c1 = new CellT(0, 0);
		c2 = new CellT(4, 0);

		b.addShip(c1, c2, 0);
		assertTrue(b.checkShip(2, 0));
	}

	@Test
	public void testBoardT_addShip_HorizontalBackwards() {
		c1 = new CellT(4, 0);
		c2 = new CellT(0, 0);

		b.addShip(c1, c2, 0);
		assertTrue(b.checkShip(2, 0));
	}

	@Test(expected = InvalidShipException.class)
	public void testBoardT_addShip_Overlapping() {
		// Overlap at point (3,9)
		b.addShip(c1, c2, 0);
		b.addShip(new CellT(3, 9), new CellT(6, 9), 1);
	}

	@Test
	public void testBoardT_checkShip() {
		b.addShip(c1, c2, 0);
		assertTrue(b.checkShip(2, 9));
	}

	@Test
	public void testBoardT_checkNotShip() {
		b.addShip(c1, c2, 0);
		assertTrue(!b.checkShip(5, 5));
	}

	@Test
	public void testBoardT_checkSunk() {
		c1 = new CellT(0, 0);
		c2 = new CellT(0, 1);
		b.addShip(c1, c2, 4);
		b.attack(c1);
		b.attack(c2);
		assertTrue(b.checkSunk(0, 0));
	}

	@Test
	public void testBoardT_checkPartiallySunk() {
		c1 = new CellT(0, 0);
		c2 = new CellT(0, 1);
		b.addShip(c1, c2, 4);
		b.attack(c1);
		assertFalse(b.checkSunk(0, 0));
	}

	@Test
	public void testBoardT_checkNotSunk() {
		c1 = new CellT(0, 0);
		c2 = new CellT(0, 1);
		b.addShip(c1, c2, 4);
		assertFalse(b.checkSunk(0, 0));
	}

	// Know that it should be 0 because no ships have been initialized
	@Test
	public void testBoardTConstructorRemainingShips() {
		assertTrue(b.remainingShipCells() == 0);
	}

	@Test
	public void testBoardT_attack_NonShipCell() {
		c1 = new CellT(0, 0);
		c2 = new CellT(0, 1);
		b.addShip(c1, c2, 4);
		b.attack(new CellT(5, 5));
		assertTrue(b.getBoardCells()[5][5].isHit());
	}

	@Test
	public void testBoardT_attack_ShipCell() {
		c1 = new CellT(0, 0);
		c2 = new CellT(0, 1);
		b.addShip(c1, c2, 4);
		b.attack(c1);
		assertTrue(b.getBoardCells()[0][0].isHit());
	}

	@Test(expected = InvalidMoveException.class)
	public void testBoardT_attack_attackedAlready() {
		c1 = new CellT(0, 0);
		c2 = new CellT(0, 1);
		b.addShip(c1, c2, 4);
		b.attack(c1);
		b.attack(c1);
	}

	@Test
	public void testBoardT_RemainingShipCells_NotZero() {
		b.addShip(c1, c2, 0);
		assertTrue(b.remainingShipCells() == 5);
	}

	@Test
	public void testBoardT_RemainingShipCells_Attacked() {
		b.addShip(c1, c2, 0);
		b.attack(c1);
		assertTrue(b.remainingShipCells() == 4);
	}

	@Test
	public void testBoardT_getBoardCells() {
		b.addShip(c1, c2, 0);
		b.attack(c1);
		CellT[][] board = b.getBoardCells();
		assertTrue(board[0][9].isHit());
	}

	@Test
	public void testBoardT_getShipsSunk() {
		b.addShip(c1, c2, 0);
		b.addShip(new CellT(0, 0), new CellT(0, 3), 1);
		CellT c3 = new CellT(9, 9);
		CellT c4 = new CellT(9, 8);
		b.addShip(c3, c4, 4);

		assertTrue(b.getShipsSunk() == 0.0);
	}

	@Test
	public void testBoardT_getShipsRemaining_afterAttacking() {
		CellT c3 = new CellT(9, 9);
		CellT c4 = new CellT(9, 8);
		b.addShip(c3, c4, 4);

		b.attack(c3);
		b.attack(c4);

		// 1/5 ships sunk:
		assertEquals(b.getShipsSunk(), 0.20, 0.0);
	}

	// Finally, we can test the Battleship module
	@Test
	public void testBattleship_SetUpP1Board() {
		BoardT b1 = new BoardT();
		b1.addShip(c1, c2, 0);
		Battleship.setP1Board(b1);
		assertTrue(Battleship.getP1Board().checkShip(c1.getX(), c1.getY()));
	}

	@Test
	public void testBattleship_SetUpP2Board() {
		BoardT b2 = new BoardT();
		b2.addShip(c1, c2, 0);
		Battleship.setP2Board(b2);
		assertTrue(Battleship.getP2Board().checkShip(c1.getX(), c1.getY()));
	}

	@Test
	public void testBattleship_getP1Board() {
		Battleship.setP1Board(b);
		assertFalse(Battleship.getP1Board().getBoardCells()[5][5].isHit());
	}

	@Test
	public void testBattleship_getP2Board() {
		Battleship.setP2Board(b);
		assertFalse(Battleship.getP2Board().getBoardCells()[5][5].isHit());
	}

	@Test
	public void testBattleship_isP1Turn() {
		// Game starts with p1;
		assertTrue(Battleship.isP1Turn());
	}

	@Test
	public void testBattleship_isP2Turn() {
		Battleship.switchTurn();
		assertTrue(!Battleship.isP1Turn());
	}

	@Test
	public void testBattleship_switchTurnNormal() {
		// Is not reset from previous test; results in P1's turn
		Battleship.switchTurn();
		Battleship.switchTurn();
		Battleship.switchTurn();
		assertTrue(Battleship.isP1Turn());
	}

	@Test
	public void testBattleship_isGameOver() {
		assertTrue(!Battleship.isGameOver());
	}

	@Test
	public void testBattleship_setGameOver() {
		Battleship.setGameOver();
		assertTrue(Battleship.isGameOver());
	}

	@Test
	public void testBattleship_P1IsWinning_Tie() {
		Battleship.setP1Board(b);
		Battleship.setP2Board(b);
		assertTrue(Battleship.p1IsWinning());
	}

	@Test
	public void testBattleship_P2IsWinning() {
		BoardT board = new BoardT();
		BoardT board2 = new BoardT();
		board.addShip(c1, c2, 0);
		board2.addShip(c1, c2, 0);
		board.attack(c1);
		board.attack(c2);
		Battleship.setP1Board(board);
		Battleship.setP2Board(board2);
		assertTrue(!Battleship.p1IsWinning());
	}

}
