/** @file InvalidCellException.java
 *  @title InvalidCellException
 *  @author Mediha Munim, munimm
 *  @date 03/26/2017
 */

/**
 * @brief Exception is thrown when an invalid cell is instantiated.
 */
public class InvalidCellException extends RuntimeException{

	public InvalidCellException(String s){
		super(s);
	}
}
