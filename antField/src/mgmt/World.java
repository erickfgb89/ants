package mgmt;

import java.util.LinkedList;
import java.util.Random;

import ants.Atom;

/**
 * 
 * Worlds, in their simplest form, contain a simple list of Ants, which they 
 * animate based on rules dictated by their parameters.
 *
 * @author Erick Aldaz
 * 
 */
public class World extends LinkedList<Atom>{
	
	private static final long serialVersionUID = -3325466507174958665L;
	/**
	 * This {@link Random} object runs the selection lottery that runs the 
	 * {@link ants.Ant}s in the {@link mgmt.World}.
	 */
	private final Random r;
	/**
	 * Height and width of this World object.
	 */
	public final int worldWidth, worldHeight;
	
	/**
	 * The World lottery selects a random {@link ants.Atom} from the world to act, rather 
	 * than iterating through all {@link ants.Atom}s in the World. 
	 * 
	 * @return Random {@link ants.Atom} from this List.
	 */
	public Atom lottery( ) {
		return this.get( r.nextInt( this.size( ) ) );
	}
	
	/**
	 * Clock Tick.  Identifies the next ant due to for a turn and 
	 * calls its {@code run( )} method
	 */
	public void tick( ) {
		lottery( ).getAnt( ).run( );
	}

	/**
	 * Takes integer arguments to represent width and height of the board,
	 * in arbitrary units (initially, pixels).  Ants can live at sub-integer
	 * positions, to double precision, to allow for zooming as agents grow and shrink.
	 * Width and Height of the board will determine the number of buckets used to
	 * represent the board, which will determine the efficiency of message-passing
	 * and proximity testing among the ants during the simulation.
	 * 
	 * @param w Initial width of the world
	 * @param h Initial height of the world
	 */
	public World( int w, int h ) {
		super( );
		r = new Random( );
		this.worldWidth = w;
		this.worldHeight = h;
	}
}
