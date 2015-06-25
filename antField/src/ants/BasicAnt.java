package ants;

import java.util.Random;

import msg.Action;
import msg.ActionMove;

/**
 * BasicAnt is a proof of concept {@link ants.Ant}.  Its {@code run( )} implementation 
 * simply chooses a random movement vector magnitude and a random delta movement angle 
 * at every step.
 * 
 * @author Erick Aldaz
 *
 */
public class BasicAnt extends Ant {

	/**
	 * Random object that gives this Ant its movement vector at every step.
	 */
	private final Random r;
	
	/**
	 * This method is called at every tick of the {@link mgmt.World} clock during which this 
	 * Ant is scheduled to run.
	 * This particular class does not do any complicated computations - it simply computes a random 
	 * movement vector at every step.
	 */
	@Override
	public Action run( ) {
		if( this.getParent( ) == null )
			return null;
		
		double forward = r.nextDouble( ) * 5;
		
		this.getParent( ).move( forward, r.nextGaussian( ) * Math.PI / 4 );
		return new ActionMove( this, r.nextGaussian( ) * Math.PI / 4, forward );
		
		
	}

	
	/**
	 * Simple {@link ants.Ant} constructor.  Calls the primary BasicAnt constructor, 
	 * with a null {@link ants.Atom} argument.
	 */
	public BasicAnt( ) {
		this( null );
	}
	
	/**
	 * Basic constructor.  Creates a basic {@link ants.Ant} with its encapsulating {@link ants.Atom} 
	 * and instantiates the Random object that governs its movement.
	 * 
	 * @param a {@link ants.Atom} object that houses this Ant
	 */
	public BasicAnt ( Atom a ) {
		super( a );
		r = new Random( );
	}
}
