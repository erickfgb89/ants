/**
 * 
 */
package ants;

import java.awt.geom.Rectangle2D.Double;

import mgmt.World;

/**
 * An {@link ants.Atom} is an atomic management unit in the world. Because {@link ants.Ant}s will 
 * (presumably) misbehave, positional variables, as well as any trusted interactions with the outside
 * {@link mgmt.World}, must go through this object, which hosts a single {@link ants.Ant} object.
 * 
 * @author Erick Aldaz
 */
public class Atom extends Double {
	private static final long serialVersionUID = 1452389607531194810L;
	
	/**
	 * This Atom's single {@code Ant} child object. 
	 * An {@code Atom} can exist without an {@code Ant}, and an {@code Ant}
	 * can exist without an {@code Atom}.
	 * 
	 * This gives us the freedom to create Ants without knowing where they'll 
	 * be placed, or create Atoms without populating them at creation time.
	 * 
	 * In the absence of an Ant, all operations requiring communications with 
	 * the Ant will return false, but will not throw an exception.
	 * 
	 * Once bound to an Ant, an Atom cannot be rebound to another Ant.
	 */
	private Ant ant;
	
	/**
	 * {@link mgmt.World} in which this Atom resides.  Used for any data requests for data 
	 * that doesn't correspond to this Atom's {@link ants.Ant} object.
	 */
	private final World world;
	/**
	 * Direction that this Atom is currently facing, in radians, where 0 is 
	 * the positive X-axis
	 */
	private double direction;
	
	/**
	 * Sets this Atom's {@link ants.Ant} object. This can only be set once, including the constructor
	 * that includes an {@link ants.Ant} argument.
	 * Subsequent calls to {@code setAnt( )} will fail.
	 * @param a Ant to be bound to this Atom.
	 * @return success / failure of binding.  Once an ant has been bound to this Atom,
	 * any subsequent calls to {@code setAnt} return false, and the assignment does not 
	 * occur.  Calls with a null Ant argument will also return false.
	 */
	public boolean setAnt( Ant a ) {
		return this.ant == null && (this.ant = a) != null;
	}
	
	public Ant getAnt( ) {
		return this.ant;
	}
	
	/**
	 * Request a move of this Atom object in its parent {@link mgmt.World}.  
	 * X and Y deltas are limited to 5, and direction delta is limited to {@code Math.PI}.
	 * 
	 * Today, this method disallows movement outside of the boundaries of the world.
	 * Movement beyond the boundaries of the world rolls the Atom around to the other 
	 * side of the World, to simulate an infinite world, while avoiding having to 
	 * alter movement direction or notify Atoms of the boundary.
	 * 
	 * @param magnitude proposed movement vector magnitude.
	 * @param dd proposed change in movement vector direction.
	 * @return success / failure of the Atom's move inside the world.
	 */
	protected boolean move( double magnitude, double dd ) {
		if( !( Math.abs( magnitude ) <= 5 && Math.abs( dd ) <= Math.PI ) )
			return false;

		this.direction += dd;
		this.x = ( this.x + magnitude * Math.cos( this.direction ) ) % this.world.worldWidth;
		this.y = (this.y + magnitude * Math.sin( this.direction ) ) % this.world.worldHeight;
		
		if( this.x < 0 ) this.x += this.world.worldWidth;
		if( this.y < 0 ) this.y += this.world.worldHeight;
		
		return true;
	}
	
	/**
	 * This constructor includes an {@link ants.Ant} object as a convenience, but is otherwise identical 
	 * to the constructor without this second argument.
	 * @param world This object's hosting {@link mgmt.World}. 
	 * @param a The {@link ants.Ant} this object will host and govern.
	 * @param x Initial X position.
	 * @param y Initial Y position.
	 * @param w Width of this object in the {@link mgmt.World}.
	 * @param h Height of this object in the {@link mgmt.World}.
	 */
	public Atom( World world, Ant a, double x, double y, double w, double h ) {
		super( x, y, w, h );
		this.ant = a;
		this.world = world;
		this.direction = 0;
	}
	
	/**
	 * This constructor excludes an {@link ants.Ant} argument. An {@link ants.Ant} object provides the 
	 * Atomic unit's intelligence, so one can imagine many cases in which no intelligence 
	 * (and thus, no {@link ants.Ant}), is necessary.
	 * @param world This object's hosting {@link mgmt.World}. 
	 * @param x Initial X position.
	 * @param y Initial Y position.
	 * @param w Width of this object in the {@link mgmt.World}.
	 * @param h Height of this object in the {@link mgmt.World}.
	 */
	public Atom( World world, double x, double y, double w, double h ) {
		this( world, null, x, y, w, h );
	}
}
