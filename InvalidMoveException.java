/** @file InvalidMoveException.java
 *  @title InvalidMoveException
 *  @author Mediha Munim, munimm
 *  @date 03/26/2017
 */

/**
 * @brief Exception is thrown when an invalid move is made.
 */
public class InvalidMoveException extends RuntimeException{

	public InvalidMoveException(String s){
		super(s);
	}
}
