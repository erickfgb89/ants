package msg;


/**
 * This interface represents a single specific action that might be taken by 
 * an actor in the world.
 * 
 * Every action by every actor in the world, from movement to consumption to passive resistance, 
 * must be represented by some class that implements {@code Action}, and wrapped in an instance of 
 * {@code ActionItem}.
 * @author Erick Aldaz
 *
 */
public interface Action {
	
	/**
	 * Print a textual description of this action to stdout instead 
	 * of painting it to a graphical interface.
	 */
	public void toConsole( );
	
	/**
	 * the Swing JComponents that paint the world will automatically update periodically with 
	 * any updates. This method should be used for any updates that are necessary only when 
	 * presenting updates via a graphical client.
	 */
	public void toPanel( );

}
