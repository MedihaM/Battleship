/** @file InvalidShipException.java
 *  @title InvalidShipException
 *  @author Mediha Munim, munimm
 *  @date 03/26/2017
 */

/**
 * @brief Exception is thrown when an invalid ship is instantiated.
 */
public class InvalidShipException extends RuntimeException{

	public InvalidShipException(String s){
		super(s);
	}
}
