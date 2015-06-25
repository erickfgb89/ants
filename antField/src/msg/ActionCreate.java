package msg;

import ants.Atom;

/**
 * @author Erick Aldaz
 * 
 * This type represents the act of creating an actor
 *
 */
public class ActionCreate implements Action {

	private final Atom subject;
	
	@Override
	public void toConsole() {
		System.out.println( this.toString( ) );
	}

	@Override
	public void toPanel() {

	}
	
	@Override
	public String toString( ) {
		return "CREATE\t(" + Integer.toHexString( subject.getAnt( ).hashCode( ) )
				+ ")\tAT\t(" + subject.x + "," + subject.y + ")";
	}
	
	/**
	 * @param Atom encapsulating the Ant being created 
	 */
	public ActionCreate( Atom a ) {
		this.subject = a;
	}

}
