package ants;

import java.util.Random;

import msg.Action;
import msg.ActionMove;


/**
 * An {@code Ant}'s run method represents a single action for an 
 * atomic actor.  No return value is expected, as messaging and 
 * status handling is done by the {@code Atom} object that encapsulates 
 * this {@code Ant}.
 * 
 * Status information such as location, as well as World details, 
 * such as environmental rules and locations of other Ants, is unavailable
 * to Ants for direct access, and must be requested from the parent Atom.
 * 
 * @author Erick Aldaz
 */
public abstract class Ant {

	/**
	 * Parent object that serves as the interface between this Ant and its surrounding {@link mgmt.World}.
	 */
	private Atom parent;

	/**
	 * In lieu of implementing Runnable, this class declares its own version of the {@code run()} 
	 * method that returns an action to be logged.
	 * 
	 * @return Action object representing the Ant's move at the current step
	 */
	public abstract Action run( );
	
	/**
	 * Sets the parent of this Ant object to the method's {@link ants.Atom} Argument.
	 * Such a binding can only occur once; after a single binding, any subsequent attempted.
	 * bindings to other Atoms will fail.
	 * @param a {@link ants.Atom} that should host this Ant object.
	 * @return true if this is a first-time assignment, false on subsequent assignments or on null.
	 */
	public boolean setParent( Atom a ) {
		return this.parent == null && (this.parent = a) != null;
	}
	
	public Atom getParent( ) {
		return this.parent;
	}
	
	/**
	 * Request a change to the physical position of this {@code Ant}.
	 * This method requests the change from the parent {@code Atom},
	 * and reports back whether the {@code Atom} accepted the change.
	 * 
	 * @param action {@code ActionMove} object representing the move requested
	 * @return the action resulting from this request, or null if the action was prevented by world rules 
	 */
	public final Action move( ActionMove action ) {
		return this.parent.move( action );
	}
	
	/**
	 * This empty constructor assumes that no {@link ants.Atom} should host this object yet.
	 * The new Ant can run as usual, but any movements or requests for input will be ignored, as it 
	 * has no context in which to communicate, until {@code setParent( )} is called.
	 */
	public Ant( ) {
		this( null );
	}
	
	/**
	 * Creates an empty Ant object with its {@code parent} reference already set.
	 * @param a {@link ants.Atom} object to host this new Ant object.
	 */
	public Ant ( Atom a ) {
		this.parent = a;
	}
}
