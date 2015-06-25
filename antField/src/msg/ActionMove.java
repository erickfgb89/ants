/**
 * 
 */
package msg;

import ants.Ant;

/**
 * @author Erick Aldaz
 * 
 * This action represents a simple move for a single actor.  This class does not 
 * do any sort of boundary or error checking - that is left to the world.
 *
 */
public class ActionMove implements Action {

	private final Ant subject;
	private final double direction, magnitude;
	
	@Override
	public void toConsole() {
		System.out.println( this.toString( ) );
	}

	@Override
	public void toPanel( ) {
		//Panel auto-paints updates.  Do nothing with this for now
	}
	
	@Override
	public String toString( ) {
		return "MOVE\t("
				+ Integer.toHexString( this.subject.hashCode( ) ) 
				+ ")\t"+magnitude+"\tTOWARD\t"+direction;
	}
	
	public ActionMove( Ant subject, double direction, double magnitude ) {
		this.subject = subject;
		this.direction = direction;
		this.magnitude = magnitude;
	}

}
