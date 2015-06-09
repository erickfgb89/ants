package ants;


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
public abstract class Ant implements Runnable {

	/**
	 * Parent object that serves as the interface between this Ant and its surrounding {@link mgmt.World}.
	 */
	private Atom parent;
	
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
	
	protected Atom getParent( ) {
		return this.parent;
	}
	
	/**
	 * Request a change to the physical position of this {@code Ant}.
	 * This method requests the change from the parent {@code Atom},
	 * and reports back whether the {@code Atom} accepted the change.
	 * 
	 * @param magnitude Magnitude of Atom movement vector.
	 * @param dd Change to Ant direction.
	 * @return success or failure of move operation, based on {@code World} rules.
	 */
	@SuppressWarnings("unused")
	private boolean move( double magnitude, double dd ) {
		return this.parent.move( magnitude, dd );
	}
	
	/**
	 * This empty constructor assumes that no {@link ants.Atom} should host this object yet.
	 * The new Ant can run as usual, but any movements or requests for input will be ignored, as it 
	 * has no context in which to communicate, until {@code setParent( )} is called.
	 */
	public Ant( ) {
		this.parent = null;
	}
	
	/**
	 * Creates an empty Ant object with its {@code parent} reference already set.
	 * @param a {@link ants.Atom} object to host this new Ant object.
	 */
	public Ant ( Atom a ) {
		this.parent = a;
	}
}
